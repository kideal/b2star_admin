package com.mall.dao;

import com.mall.entity.Payment;
import com.mall.entity.PaymentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PaymentMapper {
    long countByExample(PaymentExample example);

    int deleteByExample(PaymentExample example);

    int deleteByPrimaryKey(Integer paymentId);

    int insert(Payment record);

    int insertSelective(Payment record);

    List<Payment> selectByExample(PaymentExample example);

    Payment selectByPrimaryKey(Integer paymentId);

    int updateByExampleSelective(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByExample(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);
}