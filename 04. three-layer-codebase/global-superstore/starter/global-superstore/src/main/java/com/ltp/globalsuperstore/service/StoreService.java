package com.ltp.globalsuperstore.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repository.StoreRepository;

public class StoreService {

    StoreRepository storeRepository = new StoreRepository();

    public Item getItem(int index) {
        return storeRepository.getItem(index);
    }

    public void addItem(Item item) {
        storeRepository.addItem(item);
    }

    public void setItem(Item item, int index) {
        storeRepository.setItem(item, index);
    }

    public List<Item> getItems() {
        return storeRepository.getItems();
    }

    public int getIndexFromId(String id) {
        for (int i = 0; i < getItems().size(); i++) {
            if (getItems().get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }

    public int getItemIndex(String id) {
        for (int i = 0; i < getItems().size(); i++) {
            if (getItems().get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

    public Item getItemById(String id){
        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }

    public String getStatus(String id) {
        int index = getIndexFromId(id);
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            items.add(id);
        } else if (within5Days(getDate(), storeRepository.get(index).getDate())) {
            items.set(index, getItem);
        } else {
            status = Constants.FAILED_STATUS;
        }
        return status;
    }
}
