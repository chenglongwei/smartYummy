package com.smartYummy.service;

import com.smartYummy.model.Item;

import java.util.List;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-04-28
 * <p/>
 * item services.
 */
public interface ItemService {
    List<Item> findByCategory(String category);
    List<Item> findByCategoryAndTag(String category, int tag);
    List<Item> findByTag(int tag);
    List<Item> listAllItems();
    Item findByID(long id);
    Item insertItem(Item item);
    void setTag(Item item, int tag);
    void deleteById(long id);
}
