package com.mall.dao;

import com.mall.entity.GoodsRecord;
import com.mall.entity.GoodsRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GoodsRecordMapper {
    long countByExample(GoodsRecordExample example);

    int deleteByExample(GoodsRecordExample example);

    int deleteByPrimaryKey(Integer goodsRecordId);

    int insert(GoodsRecord record);

    int insertSelective(GoodsRecord record);

    List<GoodsRecord> selectByExample(GoodsRecordExample example);

    GoodsRecord selectByPrimaryKey(Integer goodsRecordId);

    int updateByExampleSelective(@Param("record") GoodsRecord record, @Param("example") GoodsRecordExample example);

    int updateByExample(@Param("record") GoodsRecord record, @Param("example") GoodsRecordExample example);

    int updateByPrimaryKeySelective(GoodsRecord record);

    int updateByPrimaryKey(GoodsRecord record);
}