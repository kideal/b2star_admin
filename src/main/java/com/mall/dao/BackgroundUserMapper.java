package com.mall.dao;

import com.mall.entity.BackgroundUser;
import com.mall.entity.BackgroundUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BackgroundUserMapper {
    long countByExample(BackgroundUserExample example);

    int deleteByExample(BackgroundUserExample example);

    int insert(BackgroundUser record);

    int insertSelective(BackgroundUser record);

    List<BackgroundUser> selectByExample(BackgroundUserExample example);

    int updateByExampleSelective(@Param("record") BackgroundUser record, @Param("example") BackgroundUserExample example);

    int updateByExample(@Param("record") BackgroundUser record, @Param("example") BackgroundUserExample example);
}