package com.smartYummy.repository;

import com.smartYummy.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenglongwei on 4/28/16.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(String category);
    List<Item> findByCategoryAndTag(String category, int tag);
    List<Item> findByTag(int tag);
}
