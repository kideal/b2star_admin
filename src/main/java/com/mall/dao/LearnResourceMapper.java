package com.mall.dao;

import com.mall.entity.LearnResource;
import com.mall.entity.LearnResourceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearnResourceMapper {
    long countByExample(LearnResourceExample example);

    int deleteByExample(LearnResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LearnResource record);

    int insertSelective(LearnResource record);

    List<LearnResource> selectByExample(LearnResourceExample example);

    LearnResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LearnResource record, @Param("example") LearnResourceExample example);

    int updateByExample(@Param("record") LearnResource record, @Param("example") LearnResourceExample example);

    int updateByPrimaryKeySelective(LearnResource record);

    int updateByPrimaryKey(LearnResource record);
}