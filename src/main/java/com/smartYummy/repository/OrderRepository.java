package com.smartYummy.repository;

import com.smartYummy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
    List<Order> findAllByOrderByCreateTimeAsc();
    List<Order> findByCreateTimeBetweenOrderByCreateTimeAsc(Date from, Date to);
    List<Order> findByCreateTimeBetweenOrderByStartTimeAsc(Date from, Date to);
}
