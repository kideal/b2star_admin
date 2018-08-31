package com.mall.service.impl;

import com.google.common.collect.Lists;
import com.mall.dao.GoodsAcquireMapper;
import com.mall.dao.ProductAcquireMapper;
import com.mall.entity.GoodsAcquire;
import com.mall.entity.GoodsAcquireExample;
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
    private GoodsAcquireMapper goodsAcquireMapper;

    @Autowired
    private ProductAcquireMapper productAcquireMapper;

    @Override
    @Transactional
    public GoodsAcquire NJAcquireDate(Integer goodsId, Integer brandId, String goodsNo) {
        String url = "http://www.nj-reagent.com/item/detail/" + goodsNo.substring(0, goodsNo.length() - 2) + ".htm";
        WebDriver driver = null;
        GoodsAcquire goodsAcquire = getGoodsAcquire();
        goodsAcquire.setGoodsId(goodsId);
        goodsAcquire.setBrandId(brandId);
        try {
            driver = LaunchChrome.launch(url);
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
                    goodsAcquire.setGoodsNo(tdList.get(j));
                    goodsAcquire.setPrice(new BigDecimal(tdList.get(j + 3).substring(2)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goodsAcquire.setCostPrice(goodsAcquire.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goodsAcquire.setRealPrice(goodsAcquire.getPrice().multiply(new BigDecimal("0.98")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goodsAcquire.setPublished(1);
                    goodsAcquire.setDel(false);
                    break;
                }
            }
            if (goodsAcquire.getGoodsNo() == null) {
                new BigDecimal("");
            }
        } catch (Exception e) {
            goodsAcquire.setGoodsId(goodsId);
            goodsAcquire.setGoodsNo(goodsNo);
            goodsAcquire.setPublished(0);
        }
        driver.quit();
        if (ObjectUtil.isNotEmpty(goodsAcquire.getGoodsNo())) {
            saveGoodsAcquire(goodsAcquire);
            return goodsAcquire;
        }
        return null;
    }

    @Override
    public GoodsAcquire EnergyAcquireDate(Integer goodsId, Integer brandId, String goodsNo, String specification) {
        String keyWords = goodsNo.substring(0, 7).toUpperCase();
        String url = "https://www.energy-chemical.com/search.html?key=" + keyWords;
        WebDriver driver = null;
        GoodsAcquire goodsAcquire = getGoodsAcquire();
        goodsAcquire.setGoodsId(goodsId);
        goodsAcquire.setGoodsNo(goodsNo);
        goodsAcquire.setBrandId(brandId);
        goodsAcquire.setSpecification(getSpecification(specification));
        try {
            LaunchChrome launchChrome = new LaunchChrome();
            driver = launchChrome.launch(url);
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
                    goodsAcquire.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goodsAcquire.setCostPrice(goodsAcquire.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                    goodsAcquire.setRealPrice(goodsAcquire.getPrice());
                    if (temp.get(j).split("-").length == 2) {
                        goodsAcquire.setGoodsNo(temp.get(j));
                    } else {
                        goodsAcquire.setGoodsNo(keyWords + "-" + specification);
                    }
                    goodsAcquire.setPublished(1);
                    goodsAcquire.setDel(false);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                goodsAcquire.setPublished(0);
            }
        } catch (Exception e) {
            goodsAcquire.setPublished(0);
        }
        try {
            Random rand = new Random();
            int randNumber = rand.nextInt(20 - 10 + 1) + 10;
            Thread.sleep(new Long((long) randNumber * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
        if (ObjectUtil.isNotEmpty(goodsAcquire.getGoodsNo())) {
            saveGoodsAcquire(goodsAcquire);
            return goodsAcquire;
        }
        return null;
    }

    @Override
    public List<GoodsAcquire> EnergyCrawlDate(Integer brandId, String url) {
        WebDriver mainDrive = null;
        List<GoodsAcquire> goodsAcquires = Lists.newArrayList();
        try {
            mainDrive = LaunchChrome.launch(url);
            List<WebElement> lis = mainDrive.findElement(By.className("content")).findElements(By.tagName("li"));
            try {
                for (int i = 0; i < lis.size(); i++) {
                    Thread.sleep(5000);
                    String href = lis.get(i).findElement(By.tagName("a")).getAttribute("href");
                    WebDriver driver = LaunchChrome.launch(href);
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
                        GoodsAcquire goodsAcquire = getGoodsAcquire(url);
                        int j = k * 8;
                        String[] specificationAttr = temp.get(j + 1).split("\\s+");
                        String specificationEnergy = specificationAttr[0] + specificationAttr[1];
                        goodsAcquire.setSpecification(specificationEnergy);
                        String price = temp.get(j + 4).split("/")[0].trim();
                        goodsAcquire.setPrice(new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goodsAcquire.setCostPrice(goodsAcquire.getPrice().multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
                        goodsAcquire.setRealPrice(goodsAcquire.getPrice());
                        if (temp.get(j).split("-").length == 2) {
                            goodsAcquire.setGoodsNo(temp.get(j));
                        } else {
                            goodsAcquire.setGoodsNo(temp.get(j).substring(0, 7) + "-" + specificationEnergy);
                        }
                        goodsAcquire.setParameter(parameter);
                        goodsAcquire.setProductId(productAcquire.getProductId());
                        goodsAcquire.setPublished(1);
                        goodsAcquire.setDel(false);
                        saveGoodsAcquire(goodsAcquire);
                        goodsAcquires.add(goodsAcquire);
                    }
                    Thread.sleep(3000);
                    driver.quit();
                }
            } catch (Exception e) {

            }
            mainDrive.quit();
        } catch (Exception e) {

        }
        return goodsAcquires;
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

    private GoodsAcquire getGoodsAcquire() {
        GoodsAcquire goodsAcquire = new GoodsAcquire();
        return goodsAcquire;
    }
    private GoodsAcquire getGoodsAcquire(String remark) {
        GoodsAcquire goodsAcquire = new GoodsAcquire();
        goodsAcquire.setRemark(remark);
        return goodsAcquire;
    }

    private void saveGoodsAcquire(GoodsAcquire goodsAcquire) {
        GoodsAcquireExample goodsAcquireExample = new GoodsAcquireExample();
        goodsAcquireExample.createCriteria().andGoodsNoEqualTo(goodsAcquire.getGoodsNo());
        List<GoodsAcquire> goodsAcquires = goodsAcquireMapper.selectByExample(goodsAcquireExample);
        if (ObjectUtil.isNullOrEmpty(goodsAcquires)) {
            goodsAcquire.setCreateTime(new Date());
            goodsAcquire.setUpdateTime(new Date());
            goodsAcquireMapper.insertSelective(goodsAcquire);
        } else {
            goodsAcquire.setGoodsAcquireId(goodsAcquires.get(0).getGoodsAcquireId());
            goodsAcquire.setUpdateTime(new Date());
            goodsAcquireMapper.updateByPrimaryKeySelective(goodsAcquire);
        }
    }

    public static void main(String[] args) {
        String s = "规格及纯度： 95% (stabilized with TBC)";
        System.out.println(s.substring(7));
    }
}
