package com.mall.dto;

import com.mall.entity.Goods;

/**
 * Created by huangtao on 2018/8/28
 */
public class GoodsCasDto extends Goods {
    private String cas;
    private String name;

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
