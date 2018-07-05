package com.mall.utils;

import com.mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangtao on 2018/7/5
 */
@Component
public class GoodsOptionUtil {
    private static IGoodsService goodsService;
    @Autowired
    public  void setGoodsService(IGoodsService goodsService) {
        GoodsOptionUtil.goodsService = goodsService;
    }

    public static void test() {
        goodsService.test(null);
    }
}
