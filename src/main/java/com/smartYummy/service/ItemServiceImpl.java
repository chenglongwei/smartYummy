package com.smartYummy.service;

import com.smartYummy.model.Item;
import com.smartYummy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenglongwei on 4/28/16.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository repository;

    @Override
    public List<Item> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Item> listAllItem() {
        return repository.findAll();
    }

    @Override
    public Item findByID(long id) {
        return repository.findOne(id);
    }

    @Override
    public Item insertItem(Item item) {
        return repository.save(item);
    }

    @Override
    public void setTag(Item item, int tag) {
        item.setTag(tag);
        repository.save(item);
    }

    @Override
    public void deleteById(long id) {
        repository.delete(id);
    }
}
