package com.mall.service;

import com.mall.dto.GoodsDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by huangtao on 2018/6/28
 */
public interface IGoodsService {
    List<GoodsDto> upload(MultipartFile file);

    List<GoodsDto> goodsDtoList(List<GoodsDto> goodsNoList);
}
