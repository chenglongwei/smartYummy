package com.smartYummy.service;

import com.smartYummy.model.Order;
import org.springframework.stereotype.Service;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Service
public interface OrderService {
    Order saveOrder(Order order);
}
