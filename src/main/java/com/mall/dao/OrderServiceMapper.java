package com.mall.dao;

import com.mall.dto.OrderInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by huangtao on 2018/6/22
 */

public interface OrderServiceMapper {
    List<OrderInfoDto> findOrders(Map map);

}
