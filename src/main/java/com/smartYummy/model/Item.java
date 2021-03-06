package com.smartYummy.model;

import javax.persistence.*;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-04-21
 * <p/>
 * Represent table item.
 */

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String category;
    private String picture;
    private double price;
    private int calories;
    private int prepareTime;
    private int tag;

    public Item() {
    }

    public Item(String name, String category, String picture, double price, int calories, int prepareTime, int tag) {
        this.name = name;
        this.category = category;
        this.picture = picture;
        this.price = price;
        this.calories = calories;
        this.prepareTime = prepareTime;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String categrory) {
        this.category = categrory;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(int prepareTime) {
        this.prepareTime = prepareTime;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "name: " + name + ", category: " + category + ", price: " + price + ", calories: " + calories +
                ", prepare time: " + prepareTime + ", tag: " + tag + ", picture: " + picture;
    }
}
