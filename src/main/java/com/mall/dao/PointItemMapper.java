package com.mall.dao;

import com.mall.entity.PointItem;
import com.mall.entity.PointItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointItemMapper {
    long countByExample(PointItemExample example);

    int deleteByExample(PointItemExample example);

    int deleteByPrimaryKey(Integer pointItemId);

    int insert(PointItem record);

    int insertSelective(PointItem record);

    List<PointItem> selectByExampleWithBLOBs(PointItemExample example);

    List<PointItem> selectByExample(PointItemExample example);

    PointItem selectByPrimaryKey(Integer pointItemId);

    int updateByExampleSelective(@Param("record") PointItem record, @Param("example") PointItemExample example);

    int updateByExampleWithBLOBs(@Param("record") PointItem record, @Param("example") PointItemExample example);

    int updateByExample(@Param("record") PointItem record, @Param("example") PointItemExample example);

    int updateByPrimaryKeySelective(PointItem record);

    int updateByPrimaryKeyWithBLOBs(PointItem record);

    int updateByPrimaryKey(PointItem record);
}