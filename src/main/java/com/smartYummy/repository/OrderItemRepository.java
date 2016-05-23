package com.smartYummy.repository;

import com.smartYummy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByOrderId(long orderId);
}
