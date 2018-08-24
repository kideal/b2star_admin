package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.dao.GoodsMapper;
import com.mall.entity.Goods;
import com.mall.service.IAcquireDataService;
import com.mall.utils.ObjectUtil;
import com.mall.utils.selenium.LaunchChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by huangtao on 2018/8/24
 */
@Service("IAcquireDataService")
public class AcquireDataServiceImpl implements IAcquireDataService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    @Transactional
    public Goods NJAcquireDate(String goodsNo, Integer goodsId) {
        String path = "http://www.nj-reagent.com/item/detail/" + goodsNo.substring(0, goodsNo.length() - 2) + ".htm";
        WebDriver driver = null;
        Goods goods = new Goods();
        try {
            driver = LaunchChrome.launch(path);
            WebElement proDetailNum = driver.findElement(By.className("pro_detail_num"));
            List<WebElement> tds = proDetailNum.findElements(By.tagName("td"));
            List<String> tdList = Lists.newArrayList();
            for (WebElement td : tds) {
                tdList.add(td.getText());
            }
            int num;
            int coefficient;
            if ("点击询价".equals(tdList.get(2))) {
                num = tdList.size() / 6;
                coefficient = 6;
                new BigDecimal("");
            } else {
                num = tdList.size() / 7;
                coefficient = 7;
            }

            for (int i = 0; i < num; i++) {
                int j = i * coefficient;
                if (goodsNo.equals(tdList.get(j))) {
                    //98  85
                    goods.setGoodsId(goodsId);
                    goods.setGoodsNo(tdList.get(j));
                    goods.setPrice(new BigDecimal(tdList.get(j + 3).substring(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setCostPrice(goods.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setRealPrice(goods.getPrice().multiply(new BigDecimal("0.98")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setUpdateTime(new Date());
                    break;
                }
            }
            if (goods.getGoodsNo() == null) {
                new BigDecimal("");
            }
        } catch (Exception e) {
            goods.setGoodsId(goodsId);
            goods.setGoodsNo(goodsNo);
            goods.setPublished(0);
            goods.setUpdateTime(new Date());
        }
        driver.quit();
        if (ObjectUtil.isNotEmpty(goods.getGoodsNo())) {
            goodsMapper.updateByPrimaryKeySelective(goods);
            return goods;
        }
        return null;
    }
}
