package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.OrderGoodsMapper;
import com.mall.dao.OrderServiceMapper;
import com.mall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by huangtao on 2018/6/22
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderServiceMapper orderServiceMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public PageInfo queryOrderList(Integer orderStatus,
                                   Integer payStatus,
                                   Integer pageNo,
                                   Integer limit,
                                   String start,
                                   String end) {
        HashMap map = new HashMap();
        map.put("orderStatus", orderStatus);
        map.put("payStatus", payStatus);
        PageHelper.startPage(pageNo, limit);
        return new PageInfo(orderServiceMapper.findOrders(map));

    }

}
