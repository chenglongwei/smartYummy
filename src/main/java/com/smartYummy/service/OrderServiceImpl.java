package com.smartYummy.service;

import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.repository.OrderItemRepository;
import com.smartYummy.repository.OrderRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
        }
        orderItemRepository.save(orderItems);
        return order;
    }

    @Override
    public List<Order> getOrders(long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreateTimeAsc();
    }

    @Override
    public Order findByID(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void deleteByID(long id) {
        // First delete order items, then delete order.
        orderItemRepository.deleteByOrderId(id);
        orderRepository.delete(id);
    }

    @Override
    public List<Order> findSameDayOrders(Date date) {
        List<Order> orders = orderRepository.findAll();
        List<Order> res = new ArrayList<Order>();

        for (Order order : orders) {
            if (DateUtils.isSameDay(date, order.getPickupTime())) {
                res.add(order);
            }
        }

        return res;
    }

    @Override
    public List<Order> findOrdersByCreateTimeOrderByCreateTime(Date from, Date to) {
        return orderRepository.findByCreateTimeBetweenOrderByCreateTimeAsc(from, to);
    }

    @Override
    public List<Order> findOrdersByCreateTimeOrderByStartTime(Date from, Date to) {
        return orderRepository.findByCreateTimeBetweenOrderByStartTimeAsc(from, to);
    }
}
