package com.mall.dao;

import com.mall.entity.GoodsPromotion;
import com.mall.entity.GoodsPromotionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsPromotionMapper {
    long countByExample(GoodsPromotionExample example);

    int deleteByExample(GoodsPromotionExample example);

    int deleteByPrimaryKey(Integer promotionId);

    int insert(GoodsPromotion record);

    int insertSelective(GoodsPromotion record);

    List<GoodsPromotion> selectByExample(GoodsPromotionExample example);

    GoodsPromotion selectByPrimaryKey(Integer promotionId);

    int updateByExampleSelective(@Param("record") GoodsPromotion record, @Param("example") GoodsPromotionExample example);

    int updateByExample(@Param("record") GoodsPromotion record, @Param("example") GoodsPromotionExample example);

    int updateByPrimaryKeySelective(GoodsPromotion record);

    int updateByPrimaryKey(GoodsPromotion record);
}