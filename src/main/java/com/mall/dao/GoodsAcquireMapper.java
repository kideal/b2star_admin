package com.mall.dao;

import com.mall.entity.GoodsAcquire;
import com.mall.entity.GoodsAcquireExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GoodsAcquireMapper {
    long countByExample(GoodsAcquireExample example);

    int deleteByExample(GoodsAcquireExample example);

    int deleteByPrimaryKey(Integer goodsAcquireId);

    int insert(GoodsAcquire record);

    int insertSelective(GoodsAcquire record);

    List<GoodsAcquire> selectByExample(GoodsAcquireExample example);

    GoodsAcquire selectByPrimaryKey(Integer goodsAcquireId);

    int updateByExampleSelective(@Param("record") GoodsAcquire record, @Param("example") GoodsAcquireExample example);

    int updateByExample(@Param("record") GoodsAcquire record, @Param("example") GoodsAcquireExample example);

    int updateByPrimaryKeySelective(GoodsAcquire record);

    int updateByPrimaryKey(GoodsAcquire record);
}