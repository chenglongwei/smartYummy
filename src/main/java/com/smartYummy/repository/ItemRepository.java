package com.smartYummy.repository;

import com.smartYummy.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenglongwei on 4/28/16.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByCategory(String category);
    List<Item> findAll();
}
