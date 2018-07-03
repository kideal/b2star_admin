package com.mall.common.observer.impl;


import com.mall.common.observer.Observer;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huangtao on 2018/5/16
 */
public class Receiver implements Observer {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Receiver(Object object) {
        this.object = object;
    }

    @Override
    public void update(Object object) {
        this.object = object;
        // read();

    }
}
