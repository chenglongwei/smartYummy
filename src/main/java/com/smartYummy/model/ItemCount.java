package com.smartYummy.model;


/**
 * Created by chenglongwei on 5/22/16.
 */
public class ItemCount implements Comparable<ItemCount> {
    private Item item;
    private int count;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(ItemCount o) {
        if (o.count != count) {
            return o.count - count;
        }

        return (int) (item.getId() - o.getItem().getId());
    }
}
