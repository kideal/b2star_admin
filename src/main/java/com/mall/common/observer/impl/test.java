package com.mall.common.observer.impl;


import com.mall.common.observer.Observer;

/**
 * Created by huangtao on 2018/5/16
 */
public class test {
    public static void main(String[] args) {
        Notice server = Notice.getNotice();

        Observer userZhang = new Receiver("ZhangSan");
        Observer userLi = new Receiver("LiSi");
        Observer userWang = new Receiver("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfo("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfo("JAVA是世界上最好用的语言！");
    }
}
