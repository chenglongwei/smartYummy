package com.smartYummy.service;

import com.smartYummy.model.Order;
import com.smartYummy.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Service
public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getOrders(long userId);
    Order findByID(long id);
    void deleteByID(long id);
}
