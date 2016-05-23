package com.smartYummy.repository;

import com.smartYummy.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-04-28
 * <p/>
 * Item table CRUD operations.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(String category);
    List<Item> findByCategoryAndTag(String category, int tag);
    List<Item> findByTag(int tag);
}
