package com.mall.service;

import com.mall.entity.Goods;

/**
 * Created by huangtao on 2018/8/24
 */
public interface IAcquireDataService {
    Goods NJAcquireDate(String goodsNo, Integer goodsId);

    Goods EnergyAcquireDate(Integer goodsId, String goodsNo,String specification);
}
