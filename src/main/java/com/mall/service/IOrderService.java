package com.mall.service;

import com.github.pagehelper.PageInfo;

/**
 * Created by huangtao on 2018/6/22
 */
public interface IOrderService {
    PageInfo queryOrderList(Integer orderStatus, Integer payStatus,
                            String keywords, String pageNo,String limit, String start, String end);
}
