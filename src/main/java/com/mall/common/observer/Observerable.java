package com.mall.common.observer;



/**
 * Created by huangtao on 2018/5/16
 */
public interface Observerable {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();
}
