package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.OrderGoodsMapper;
import com.mall.dao.OrderServiceMapper;
import com.mall.dto.OrderInfoDto;
import com.mall.entity.OrderGoods;
import com.mall.entity.OrderGoodsExample;
import com.mall.entity.User;
import com.mall.service.IOrderService;
import com.mall.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangtao on 2018/6/22
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderServiceMapper orderServiceMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm");

    @Override
    public PageInfo queryOrderList(Integer orderStatus,
                                   Integer payStatus,
                                   String keywords,
                                   String pageNo,
                                   String limit,
                                   String start,
                                   String end) {
        String s = orderServiceMapper.selectByPrimaryKey();
        System.out.println(s);
        return getOrdersPage(orderStatus, payStatus, keywords,1,
                10, null, null, null, null);
    }

    private PageInfo<OrderInfoDto> getOrdersPage(Integer orderStatus, Integer payStatus,
                                                 String keywords, Integer pageNo,
                                                 Integer limit, User buyer,
                                                 User seller, Date startDate,
                                                 Date endDate) {
        PageHelper.startPage(1, 10);
        List<OrderInfoDto> orders = getOrders(null, orderStatus, payStatus, keywords, buyer, seller, startDate, endDate);
        for (int i = 0; i < orders.size(); i++) {
            List<OrderGoods> orderGoods = getOrderGoods(orders.get(i).getOrderId());
            orders.get(i).setOrderGoods(orderGoods);
        }
        return new PageInfo<>(orders);
    }

    private List<OrderInfoDto> getOrders(Integer orderId, Integer orderStatus,
                                         Integer payStatus, String keywords,
                                         User buyer, User seller,
                                         Date startDate, Date endDate) {
        Map map = new HashMap<>(); //Mon Sep 04 00:00:00 CST 2017
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        map.put("orderId", orderId);
        map.put("orderStatus", orderStatus);
        map.put("payStatus", payStatus);
        map.put("keywords", keywords);
        map.put("buyerId", ObjectUtil.isNullOrEmpty(buyer) ? null : buyer.getUserId());
        map.put("sellerId", ObjectUtil.isNullOrEmpty(seller) ? null : seller.getUserId());
        List<OrderInfoDto> orders = orderServiceMapper.findOrders(map);
        return orders;
    }

    public List<OrderGoods> getOrderGoods(Integer orderId) {
        if (ObjectUtil.isNullOrEmpty(orderId)) {
            return null;
        }
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        return orderGoods;
    }
}
