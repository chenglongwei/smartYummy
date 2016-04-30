package com.smartYummy.service;

import com.smartYummy.model.Item;

import java.util.List;

/**
 * Created by chenglongwei on 4/28/16.
 */
public interface ItemService {
    List<Item> findByCategory(String category);
    List<Item> listAllItem();
    Item findByID(long id);
    Item insertItem(Item item);
    void setTag(Item item, int tag);
    void deleteById(long id);
}
