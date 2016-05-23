package com.smartYummy.controller;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.model.YummyResponse;
import com.smartYummy.service.EmailService;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import com.smartYummy.service.ShoppingCartService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    void addItem(@RequestParam("item_id") long item_id,
                 @RequestParam("quantity") int quantity,
                 Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.addItem(orderItem);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    void updateItem(@RequestParam("item_id") long item_id,
                    @RequestParam("quantity") int quantity,
                    Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.updateItem(orderItem);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    void removeItem(@RequestParam("item_id") long item_id,
                    Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        shoppingCartService.removeItem(orderItem);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    String getItems(Model model) {
        List<OrderItem> orderItems = shoppingCartService.getOrderItems();
        model.addAttribute("order_items", orderItems);
        return "shopping/list";
    }

    @RequestMapping(value = "/picktime", method = RequestMethod.GET)
    String pickTime(Model model) {
        return "shopping/picktime";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    YummyResponse save(@RequestParam("year") int year,
                       @RequestParam("month") int month,
                       @RequestParam("day") int day,
                       @RequestParam("hour") int hour,
                       @RequestParam("minute") int minute,
                       Authentication authentication) {

        YummyResponse response = new YummyResponse();

        // month start with 0
        month--;

        // pickupDate
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);
        Date pickupTime = date.getTime();
        System.out.println("pickup time " + pickupTime.toString());

        // order time
        List<OrderItem> orderItems = shoppingCartService.getOrderItems();
        int orderPrepareTime = getOrderPrepareTime(orderItems);

        Date startDate = fulfillStartTime(orderPrepareTime, pickupTime);
        if (startDate == null) {
            String error = "pickup time could not fulfill, the earliest time is ";
            error += getEarliestDate(getOrderPrepareTime(orderItems));
            response.setError(error);
            response.setStatus("fail");
            return response;
        }

        Order order = new Order();
        // insert user bean
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        order.setUser(currentUser.getUser());
        System.out.println(currentUser.getUser());

        order.setOrderItems(shoppingCartService.getOrderItems());
        // user pickup time
        order.setPickupTime(pickupTime);
        // prepare time
        order.setPrepareTime(orderPrepareTime);
        // order start time
        order.setStartTime(startDate);
        // order create time
        order.setCreateTime(new Date());
        // order finish time
        order.setFinishTime(DateUtils.addMinutes(startDate, orderPrepareTime));
        order.setStatus(Order.NOT_STARTED);

        // set total price
        order.setTotalPrice(shoppingCartService.getTotalPrice());

        order = orderService.saveOrder(order);

        // send email
        String subject = "Order placement success";
        String text = "Order id: " + order.getId() + ", price: " + order.getTotalPrice() +
                "\n Order details" + order.getOrderItems();

        try {
            emailService.sendEmail(currentUser.getUser(), subject, text);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setStatus("success");

        // clear shopping cart
        shoppingCartService.clear();
        return response;
    }

    private OrderItem getOrderItem(Authentication authentication, long item_id) {
        OrderItem orderItem = new OrderItem();
        // insert item bean
        orderItem.setItem(itemService.findByID(item_id));
        // insert user bean
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        orderItem.setUser(currentUser.getUser());
        System.out.println(currentUser.getUser());
        return orderItem;
    }

    /**
     * Get the possible startdate of this order, if null, could not fulfill.
     *
     * @param prepareTime
     * @param pickupTime
     * @return
     */
    private Date fulfillStartTime(int prepareTime, Date pickupTime) {
        if (!basicCheck(prepareTime, pickupTime)) {
            return null;
        }

        List<Order> orders = orderService.findSameDayOrders(pickupTime);

        // finish time before pickupTime
        for (int i = 0; i < 60; i++) {
            // tried startDate and endDate of this order
            Date startDate = DateUtils.addMinutes(pickupTime, -(i + prepareTime));
            Date endDate = DateUtils.addMinutes(startDate, prepareTime);

            int gapCount = 0;
            for (Order order : orders) {
                if (!order.getStatus().equals(Order.FINISHED)) {
                    Date orderStartDate = order.getStartTime();
                    Date orderEndDate = DateUtils.addMinutes(orderStartDate, order.getPrepareTime());

                    boolean notGap = orderEndDate.before(startDate) || endDate.before(orderStartDate);
                    if (!notGap) {
                        gapCount++;
                        if (gapCount >= 3) {
                            break;
                        }
                    }
                }
            }

            // find one valid startDate
            if (gapCount < 3) {
                return startDate;
            }
        }

        return null;
    }

    /**
     * Basic check of this submitted order,
     * check pickup time is possible,
     * check between 6:00 to 21:00,
     * check in 30 days from now.
     *
     * @param prepareTime
     * @param pickupTime
     * @return
     */
    private boolean basicCheck(int prepareTime, Date pickupTime) {
        // if pickupTime is before now + prepareTime, we are not able to fulfill.
        if (pickupTime.before(new Date(System.currentTimeMillis() + prepareTime * 60 * 1000))) {
            return false;
        }

        // if pickupTime is before 6:00 or after 9:00, we are not able to fulfill.
        Date statTime = DateUtils.addMinutes(pickupTime, -prepareTime);
        long startHour = DateUtils.getFragmentInHours(statTime, Calendar.DAY_OF_YEAR);
        if (startHour < 6) {
            System.out.println("start hour is " + startHour);
            return false;
        }
        long endHour = DateUtils.getFragmentInDays(pickupTime, Calendar.DAY_OF_YEAR);
        if (endHour > 21) {
            System.out.println("end hour is " + endHour);
            return false;
        }

        // only allow 30 days
        if (pickupTime.after(DateUtils.addDays(new Date(), 30))) {
            System.out.println("pickup time exceeds 30 days");
            return false;
        }

        return true;
    }

    private Date getEarliestDate(int prepareTime) {
        // Get today's close time.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date closeDate = calendar.getTime();
        Date latestDate = DateUtils.addMinutes(closeDate, -prepareTime);

        // get the earliest possible time;
        Date current = new Date(System.currentTimeMillis());

        while (current.compareTo(latestDate) < 0) {
            Date res = fulfillStartTime(prepareTime, current);
            if (res != null) {
                return current;
            }

            current = DateUtils.addMinutes(current, 1);
        }

        return null;
    }

    private int getOrderPrepareTime(List<OrderItem> itemList) {
        int orderPrepareTime = 0;
        for (OrderItem orderItem : itemList) {
            orderPrepareTime += orderItem.getQuantity() * orderItem.getItem().getPrepareTime();
        }
        return orderPrepareTime;
    }
}
