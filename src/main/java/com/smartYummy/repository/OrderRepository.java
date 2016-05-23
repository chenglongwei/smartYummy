package com.smartYummy.repository;

import com.smartYummy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-05-09
 * <p/>
 * Order table CRUD operations.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
    List<Order> findAllByOrderByCreateTimeAsc();
    List<Order> findByCreateTimeBetweenOrderByCreateTimeAsc(Date from, Date to);
    List<Order> findByCreateTimeBetweenOrderByStartTimeAsc(Date from, Date to);
    List<Order> findByStatus(String status);
}
