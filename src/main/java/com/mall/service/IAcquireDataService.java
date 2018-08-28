package com.mall.service;

import com.mall.entity.Goods;

/**
 * Created by huangtao on 2018/8/24
 */
public interface IAcquireDataService {
    Goods NJAcquireDate(Integer goodsId,Integer brandId,String goodsNo);

    Goods EnergyAcquireDate(Integer goodsId,Integer brandId, String goodsNo,String specification);
}
