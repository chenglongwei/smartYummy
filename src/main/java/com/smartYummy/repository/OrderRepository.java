package com.smartYummy.repository;

import com.smartYummy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
