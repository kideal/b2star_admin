package com.mall.dao.consumized;

import com.mall.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by huangtao on 2018/8/28
 */
@Mapper
public interface GoodsServiceMapper {
    int insertSelective(Goods record);
    Goods selectByPrimaryKey(Integer goodsId);
    int updateByPrimaryKeySelective(Goods record);
}
