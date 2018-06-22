package com.mall.dao;

import com.mall.entity.OrderAddress;
import com.mall.entity.OrderAddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderAddressMapper {
    long countByExample(OrderAddressExample example);

    int deleteByExample(OrderAddressExample example);

    int deleteByPrimaryKey(Integer orderAddressId);

    int insert(OrderAddress record);

    int insertSelective(OrderAddress record);

    List<OrderAddress> selectByExample(OrderAddressExample example);

    OrderAddress selectByPrimaryKey(Integer orderAddressId);

    int updateByExampleSelective(@Param("record") OrderAddress record, @Param("example") OrderAddressExample example);

    int updateByExample(@Param("record") OrderAddress record, @Param("example") OrderAddressExample example);

    int updateByPrimaryKeySelective(OrderAddress record);

    int updateByPrimaryKey(OrderAddress record);
}