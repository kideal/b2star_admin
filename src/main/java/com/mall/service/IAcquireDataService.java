package com.mall.service;

import com.mall.entity.Goods;
import com.mall.entity.GoodsAcquire;

import java.util.List;

/**
 * Created by huangtao on 2018/8/24
 */
public interface IAcquireDataService {
    GoodsAcquire NJAcquireDate(Integer goodsId, Integer brandId, String goodsNo);

    GoodsAcquire EnergyAcquireDate(Integer goodsId, Integer brandId, String goodsNo, String specification);

    List<GoodsAcquire> EnergyCrawlDate(Integer brandId, String url);
}
