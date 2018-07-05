package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.dao.GoodsMapper;
import com.mall.dao.ProductMapper;
import com.mall.dto.GoodsDto;
import com.mall.entity.Goods;
import com.mall.entity.GoodsExample;
import com.mall.entity.Product;
import com.mall.service.IGoodsService;
import com.mall.utils.Excel2007Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
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
    public void test(HashMap<String,String> map) {
        Product product = productMapper.selectByPrimaryKey(1);
        System.out.println(product);
       // System.out.println(map);
    }

    @Override
    public List<GoodsDto> upload(MultipartFile file) {
        try {
            InputStream is = file.getInputStream();
            File tempFile = new File("d:\\" + File.separator + "temp.xlsx");
            if (tempFile.exists()) {
                tempFile.mkdirs();
            }
            OutputStream os = new FileOutputStream(tempFile);
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b,0,len);
            }
            is.close();
            os.close();
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                     //  Excel2007Reader.processOneSheet(tempFile.getPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
           thread.start();
            return null;
        } catch (Exception e) {

            return null;
        }
    }
}
