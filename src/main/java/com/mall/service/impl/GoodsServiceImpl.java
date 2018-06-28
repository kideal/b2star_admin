package com.mall.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.google.common.collect.Lists;
import com.mall.dao.GoodsMapper;
import com.mall.dao.ProductMapper;
import com.mall.dto.GoodsDto;
import com.mall.entity.Goods;
import com.mall.entity.GoodsExample;
import com.mall.entity.Product;
import com.mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by huangtao on 2018/6/28
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<GoodsDto> goodsDtoList(List<GoodsDto> goodsNoList) {
        List<GoodsDto> tempList = Lists.newArrayList();
        for (GoodsDto goodsDto : goodsNoList) {
            String goodsNo = goodsDto.getGoodsNo();
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andGoodsNoEqualTo(goodsNo);
            Goods goods = goodsMapper.selectByExample(goodsExample).get(0);
            Product product = productMapper.selectByPrimaryKey(goods.getProductId());
            goodsDto.setName(product.getName());
            tempList.add(goodsDto);
            System.out.println(goodsDto);
        }
        return tempList;
    }

    @Override
    public List<GoodsDto> upload(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            ImportParams params = new ImportParams();
            List<GoodsDto> goodsNoList = ExcelImportUtil.importExcel(is, GoodsDto.class, params);
            return goodsDtoList(goodsNoList);
        } catch (Exception e) {
            System.out.println("报错了！"+e.toString());
            return null;
        }
    }
}
