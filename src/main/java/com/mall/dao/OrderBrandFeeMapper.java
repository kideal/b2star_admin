package com.mall.dao;

import com.mall.entity.OrderBrandFee;
import com.mall.entity.OrderBrandFeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderBrandFeeMapper {
    long countByExample(OrderBrandFeeExample example);

    int deleteByExample(OrderBrandFeeExample example);

    int deleteByPrimaryKey(Integer feeId);

    int insert(OrderBrandFee record);

    int insertSelective(OrderBrandFee record);

    List<OrderBrandFee> selectByExample(OrderBrandFeeExample example);

    OrderBrandFee selectByPrimaryKey(Integer feeId);

    int updateByExampleSelective(@Param("record") OrderBrandFee record, @Param("example") OrderBrandFeeExample example);

    int updateByExample(@Param("record") OrderBrandFee record, @Param("example") OrderBrandFeeExample example);

    int updateByPrimaryKeySelective(OrderBrandFee record);

    int updateByPrimaryKey(OrderBrandFee record);
}