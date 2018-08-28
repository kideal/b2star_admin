package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.dao.GoodsMapper;
import com.mall.dao.consumized.GoodsServiceMapper;
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
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by huangtao on 2018/8/24
 */
@Service("IAcquireDataService")
public class AcquireDataServiceImpl implements IAcquireDataService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsServiceMapper goodsServiceMapper;

    @Override
    @Transactional
    public Goods NJAcquireDate(Integer goodsId, Integer brandId, String goodsNo) {
        String path = "http://www.nj-reagent.com/item/detail/" + goodsNo.substring(0, goodsNo.length() - 2) + ".htm";
        WebDriver driver = null;
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setBrandId(brandId);
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
                    goods.setGoodsNo(tdList.get(j));
                    goods.setPrice(new BigDecimal(tdList.get(j + 3).substring(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setCostPrice(goods.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setRealPrice(goods.getPrice().multiply(new BigDecimal("0.98")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setPublished(1);
                    goods.setDel(false);
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

    @Override
    public Goods EnergyAcquireDate(Integer goodsId, Integer brandId, String goodsNo, String specification) {
        String keyWords = goodsNo.substring(0, 7).toUpperCase();
        String path = "https://www.energy-chemical.com/search.html?key=" + keyWords;
        WebDriver driver = null;
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsNo(goodsNo);
        goods.setBrandId(brandId);
        goods.setSpecification(getSpecification(specification));
        goods.setUpdateTime(new Date());
        try {
            driver = LaunchChrome.launch(path);
            List<WebElement> elements = driver.findElement(By.className("proPkg")).findElements(By.tagName("td"));
            List<String> temp = Lists.newArrayList();
            elements.forEach(element -> temp.add(element.getText()));
            int num = temp.size() / 8;
            boolean flag = true;
            for (int i = 0; i < num && flag; i++) {
                int j = i * 8;

                String[] specificationAttr = temp.get(j + 1).split("\\s+");
                String specificationEnergy = specificationAttr[0] + specificationAttr[1];
                specification = getSpecification(specification);
                if (specificationEnergy.equals(specification)) {
                    if (temp.get(j).split("-").length != 0) {
                        goods.setGoodsNo(temp.get(j));
                        String price = temp.get(j + 4).split("/")[0].trim();
                        goods.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goods.setCostPrice(goods.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goods.setRealPrice(goods.getPrice());
                    } else {
                        goods.setGoodsNo(keyWords + "-" + specification);
                    }
                    goods.setPublished(1);
                    goods.setDel(false);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                goods.setPublished(0);
            }
        } catch (Exception e) {
            goods.setPublished(0);
        }
        try {
            Random rand = new Random();
            int randNumber = rand.nextInt(20 - 10 + 1) + 10;
            Thread.sleep(new Long((long) randNumber * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
        if (ObjectUtil.isNotEmpty(goods.getGoodsNo())) {
            if (ObjectUtil.isNotEmpty(goodsServiceMapper.selectByPrimaryKey(goodsId))) {
                goodsServiceMapper.updateByPrimaryKeySelective(goods);
            } else {
                goodsServiceMapper.insertSelective(goods);
            }
            return goods;
        }
        return null;
    }

    private static String getSpecification(String specification) {
        if (0 == specification.split("\\.").length) {
            return specification;
        } else {
            String regex = "[a-zA-Z]";
            String[] strings = specification.split(regex);
            return strings[0].split("\\.")[0] + specification.substring(strings[0].length());
        }
    }

    public static void main(String[] args) {
        System.out.println(new Long((long) 15 * 1000));
    }

}
