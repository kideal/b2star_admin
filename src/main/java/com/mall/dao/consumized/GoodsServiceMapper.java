package com.mall.dao.consumized;

import com.mall.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by huangtao on 2018/8/28
 */
@Mapper
public interface GoodsServiceMapper {
    int insertSelective(Goods record);
    Goods selectByPrimaryKey(Integer goodsId);
    int updateByPrimaryKeySelective(Goods record);

    List<Goods> selectByGoodsNo(String goodsNo);
}
