package com.mall.service;

import com.mall.entity.Goods;

import java.util.List;

/**
 * Created by huangtao on 2018/8/24
 */
public interface IAcquireDataService {
    Goods NJAcquireDate(Integer goodsId,Integer brandId,String goodsNo,String path);

    Goods EnergyAcquireDate(Integer goodsId,Integer brandId, String goodsNo,String specification,String path);

    List<Goods> EnergyCrawlDate(Integer brandId, String path, String url);
}
