package com.mall.dto;

import com.mall.entity.*;

import java.util.List;

/**
 * Created by huangtao on 2018/6/22
 */
public class OrderInfoDto extends Orders {
    private String acceptName;

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }
}
