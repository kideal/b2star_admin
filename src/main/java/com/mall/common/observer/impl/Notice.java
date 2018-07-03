package com.mall.common.observer.impl;


import com.google.common.collect.Lists;
import com.mall.common.observer.Observer;
import com.mall.common.observer.Observerable;

import java.util.HashMap;
import java.util.List;


/**
 * Created by huangtao on 2018/5/16
 */
public class Notice implements Observerable {
    private Object object;

    private List<Observer> list;

    private Notice() {
        list = Lists.newArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        list.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < list.size(); i++) {
            Observer observer = list.get(i);
            observer.update(object);
        }
    }

    private static Notice notice = new Notice();

    public static Notice getNotice() {
        return notice;
    }

    public void setInfo(Object object) {
        this.object = object;
        notifyObserver();
    }
}
