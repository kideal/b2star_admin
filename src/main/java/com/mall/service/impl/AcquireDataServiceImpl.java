package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.dao.GoodsMapper;
import com.mall.dao.ProductAcquireMapper;
import com.mall.dao.consumized.GoodsServiceMapper;
import com.mall.entity.Goods;
import com.mall.entity.GoodsExample;
import com.mall.entity.ProductAcquire;
import com.mall.entity.ProductAcquireExample;
import com.mall.service.IAcquireDataService;
import com.mall.utils.LaunchChrome;
import com.mall.utils.ObjectUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ProductAcquireMapper productAcquireMapper;

    @Override
    @Transactional
    public Goods NJAcquireDate(Integer goodsId, Integer brandId, String goodsNo, String path) {
        String url = "http://www.nj-reagent.com/item/detail/" + goodsNo.substring(0, goodsNo.length() - 2) + ".htm";
        WebDriver driver = null;
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setBrandId(brandId);
        try {
            driver = LaunchChrome.launch(url, path);
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
            if (ObjectUtil.isNotEmpty(goods.getGoodsNo())) {
                if (ObjectUtil.isNotEmpty(goodsServiceMapper.selectByPrimaryKey(goodsId))) {
                    goodsServiceMapper.updateByPrimaryKeySelective(goods);
                } else {
                    goodsServiceMapper.insertSelective(goods);
                }
                return goods;
            }
            return goods;
        }
        return null;
    }

    @Override
    public Goods EnergyAcquireDate(Integer goodsId, Integer brandId, String goodsNo, String specification, String path) {
        String keyWords = goodsNo.substring(0, 7).toUpperCase();
        String url = "https://www.energy-chemical.com/search.html?key=" + keyWords;
        WebDriver driver = null;
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsNo(goodsNo);
        goods.setBrandId(brandId);
        goods.setSpecification(getSpecification(specification));
        goods.setUpdateTime(new Date());
        try {
            LaunchChrome launchChrome = new LaunchChrome();
            driver = launchChrome.launch(url, path);
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
                    String price = temp.get(j + 4).split("/")[0].trim();
                    goods.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setCostPrice(goods.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goods.setRealPrice(goods.getPrice());
                    if (temp.get(j).split("-").length == 2) {
                        goods.setGoodsNo(temp.get(j));
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
            saveGoods(goods);
            return goods;
        }
        return null;
    }

    @Override
    public List<Goods> EnergyCrawlDate(Integer brandId, String path, String url) {
        WebDriver mainDrive = null;
        List<Goods> goodsList = Lists.newArrayList();
        try {
            mainDrive = LaunchChrome.launch(url, path);
            List<WebElement> lis = mainDrive.findElement(By.className("content")).findElements(By.tagName("li"));
            try {
                for (int i = 0; i < lis.size(); i++) {
                    Thread.sleep(5000);
                    String href = lis.get(i).findElement(By.tagName("a")).getAttribute("href");
                    WebDriver driver = LaunchChrome.launch(href, path);
                    List<WebElement> trs = driver.findElement(By.className("re_lttab")).findElements(By.tagName("tr"));
                    ProductAcquire productAcquire = new ProductAcquire();
                    productAcquire.setCnName(trs.get(1).findElements(By.tagName("td")).get(2).getText().split(" ")[0].trim());
                    productAcquire.setEnName(trs.get(2).findElements(By.tagName("td")).get(1).getText().split(" ")[0].trim());
                    productAcquire.setCas(trs.get(3).findElements(By.tagName("td")).get(1).getText().trim());
                    productAcquire.setFormula(trs.get(4).findElements(By.tagName("td")).get(1).getText().trim());
                    productAcquire.setFormulaWeight(trs.get(4).findElements(By.tagName("td")).get(3).getText().trim());
                    ProductAcquireExample productAcquireExample = new ProductAcquireExample();
                    productAcquireExample.createCriteria().andCasEqualTo(productAcquire.getCas());
                    List<ProductAcquire> productAcquires = productAcquireMapper.selectByExample(productAcquireExample);
                    if (ObjectUtil.isNullOrEmpty(productAcquires)) {
                        productAcquire.setCreateTime(new Date());
                        productAcquire.setUpdateTime(new Date());
                        productAcquireMapper.insertSelective(productAcquire);
                    } else {
                        productAcquire.setProductId(productAcquires.get(0).getProductId());
                    }
                    WebElement reLttabb = driver.findElement(By.className("result_bor")).findElements(By.tagName("table")).get(1);
                    String parameter = reLttabb.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(2).getText().substring(7).trim();
                    List<WebElement> elements = reLttabb.findElement(By.className("proPkg")).findElements(By.tagName("td"));
                    List<String> temp = Lists.newArrayList();
                    elements.forEach(element -> temp.add(element.getText()));
                    int num = temp.size() / 8;
                    for (int k = 0; k < num; k++) {
                        Goods goods = new Goods();
                        int j = k * 8;
                        String[] specificationAttr = temp.get(j + 1).split("\\s+");
                        String specificationEnergy = specificationAttr[0] + specificationAttr[1];
                        goods.setSpecification(specificationEnergy);
                        String price = temp.get(j + 4).split("/")[0].trim();
                        goods.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goods.setCostPrice(goods.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goods.setRealPrice(goods.getPrice());
                        if (temp.get(j).split("-").length == 2) {
                            goods.setGoodsNo(temp.get(j));
                        } else {
                            goods.setGoodsNo(temp.get(j).substring(0, 7) + "-" + specificationEnergy);
                        }
                        goods.setParameter(parameter);
                        goods.setProductId(productAcquire.getProductId());
                        goods.setUpdateTime(new Date());
                        goods.setPublished(1);
                        goods.setDel(false);
                        saveGoods(goods);
                        goodsList.add(goods);
                    }
                    Thread.sleep(3000);
                    driver.quit();
                }
            } catch (Exception e) {

            }
            mainDrive.quit();
        } catch (Exception e) {

        }
        return goodsList;
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

    private void saveGoods(Goods goods) {
        List<Goods> goodsList1 = goodsServiceMapper.selectByGoodsNo(goods.getGoodsNo());
        if (ObjectUtil.isNullOrEmpty(goodsList1)) {
            goodsServiceMapper.insertSelective(goods);
        } else {
            goods.setGoodsId(goodsList1.get(0).getGoodsId());
            goodsServiceMapper.updateByPrimaryKeySelective(goods);
        }
    }

    public static void main(String[] args) {
        String s = "规格及纯度： 95% (stabilized with TBC)";
        System.out.println(s.substring(7));
    }
}
