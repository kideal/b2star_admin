package com.mall.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * Created by huangtao on 2018/6/28
 */
public class GoodsDto implements Serializable {
    @Excel(name = "goodsNo")
    String goodsNo;
    String name;

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
