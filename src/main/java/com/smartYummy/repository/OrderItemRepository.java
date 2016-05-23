package com.smartYummy.repository;

import com.smartYummy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-05-09
 * <p/>
 * OrderItem table CRUD operations.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByOrderId(long orderId);
}
