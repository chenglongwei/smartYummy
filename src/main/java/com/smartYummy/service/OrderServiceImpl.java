package com.smartYummy.service;

import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.repository.OrderItemRepository;
import com.smartYummy.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        orderItemRepository.save(orderItems);
        return order;
    }
}
