package com.smartYummy.service;

import com.smartYummy.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Service
public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getOrders(long userId);
    List<Order> getAllOrders();
    Order findByID(long id);
    void deleteByID(long id);
    List<Order> findSameDayOrders(Date date);
    List<Order> findOrdersByCreateTimeOrderByCreateTime(Date from, Date to);
    List<Order> findOrdersByCreateTimeOrderByStartTime(Date from, Date to);
    void resetOrders();
}
