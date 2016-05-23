package com.smartYummy;

import com.smartYummy.model.Order;
import com.smartYummy.service.EmailService;
import com.smartYummy.service.OrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/10/16.
 */
@Component
public class ScheduledTasks {
    @Autowired
    private OrderService orderService;
    @Autowired
    private EmailService emailService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(cron = "0 0 5 * * ?")
    @Scheduled(fixedDelay = 50000)
    public void chief1() {
        System.out.println("worker1, threadId: " + Thread.currentThread().getId() +
                " start to get order, time " + dateFormat.format(new Date()));
        worker("chief1");
    }

//    @Scheduled(cron = "0 0 5 * * ?")
    @Scheduled(fixedDelay = 50000)
    public void chief2() {
        System.out.println("worker2, threadId: " + Thread.currentThread().getId() +
                " start to get order, time " + dateFormat.format(new Date()));
        worker("chief2");
    }

//    @Scheduled(cron = "0 0 5 * * ?")
    @Scheduled(fixedDelay = 50000)
    public void chief3() {
        System.out.println("worker3, threadId: " + Thread.currentThread().getId() +
                " start to get order, time " + dateFormat.format(new Date()));
        worker("chief3");
    }

    synchronized private Order pickOrder() {
        Date current = new Date();
        List<Order> orders = orderService.findSameDayOrders(current);
        for (Order order : orders) {
            if (order.getStatus().equals(Order.NOT_STARTED)) {
                Date earliestStartTime = DateUtils.addMinutes(order.getPickupTime(),
                        -(60 + order.getPrepareTime()));
                if (current.after(earliestStartTime)) {
                    order.setStatus(Order.STARTED);
                    // update start time and finish time
                    order.setStartTime(current);
                    order.setFinishTime(DateUtils.addMinutes(current, order.getPrepareTime()));
                    orderService.saveOrder(order);

                    // send email
                    String subject = "Order starts to prepare";
                    String text = "Order id is: " + order.getId() + "\nReady to pickup time is " + order.getFinishTime();
                    try {
                        emailService.sendEmail(order.getUser(), subject, text);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return order;
                }
            }
        }

        return null;
    }

    private void worker(String chiefId) {
        while (true) {
            if (endOfToday()) {
                try {
                    // rest for one hour
                    Thread.sleep(DateUtils.MILLIS_PER_HOUR);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Order order = pickOrder();
            if (order != null) {
                System.out.println(chiefId + ", threadId " + Thread.currentThread().getId() +
                        " , order " + order.getId() + " begin to cooke");
                try {
                    // emulate cooking
                    Thread.sleep(order.getPrepareTime() * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                order.setStatus(Order.FINISHED);
                orderService.saveOrder(order);
                System.out.println(chiefId + ", order " + order.getId() + " finish cooking");
            } else {
                System.out.println(chiefId + ", threadID " + Thread.currentThread().getId() + " is free");
                try {
                    // have a rest
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean endOfToday() {
        long currentHour = DateUtils.getFragmentInDays(new Date(), Calendar.DAY_OF_YEAR);
        return currentHour >= 21;
    }
}
