package com.mall.dao;

import com.mall.entity.ProductAcquire;
import com.mall.entity.ProductAcquireExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProductAcquireMapper {
    long countByExample(ProductAcquireExample example);

    int deleteByExample(ProductAcquireExample example);

    int deleteByPrimaryKey(Integer productId);

    int insert(ProductAcquire record);

    int insertSelective(ProductAcquire record);

    List<ProductAcquire> selectByExample(ProductAcquireExample example);

    ProductAcquire selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") ProductAcquire record, @Param("example") ProductAcquireExample example);

    int updateByExample(@Param("record") ProductAcquire record, @Param("example") ProductAcquireExample example);

    int updateByPrimaryKeySelective(ProductAcquire record);

    int updateByPrimaryKey(ProductAcquire record);
}