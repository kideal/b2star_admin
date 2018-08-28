package com.mall.dao.consumized;

import com.mall.dto.GoodsCasDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by huangtao on 2018/8/28
 */
@Mapper
public interface GoodsServiceMapper {
    List<GoodsCasDto> selectByBrandId(Integer brandId);
}
