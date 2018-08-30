package com.mall.utils;

import com.google.common.collect.Lists;
import com.mall.entity.Goods;
import com.mall.entity.ProductAcquire;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.ResourceUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangtao on 2018/8/23
 */
public class LaunchChrome {
    public static WebDriver launch(String url, String path) {
        System.setProperty("webdriver.chrome.driver", path);
        //初始化一个chrome浏览器实例，实例名称叫driver
        WebDriver driver = new ChromeDriver();
        //最大化窗口
        driver.manage().window().maximize();
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // get()打开一个站点
        driver.get(url);
        //getTitle()获取当前页面title的值

        return driver;

        //关闭并退出浏览器
        //driver.quit();

    }

    public static void main(String[] args) throws Exception {
        System.out.println("开始了。。。");
        String path = ResourceUtils.getURL("classpath:static/selenium/chromedriver.exe").getPath();
        WebDriver driver1 = launch("https://www.energy-chemical.com/product/regentPro?o=1&pc=2", path);
        WebElement content = driver1.findElement(By.className("content"));
        List<WebElement> lis = content.findElements(By.tagName("li"));
        List<ProductAcquire> productAcquires = Lists.newArrayList();
        for (int i = 0; i < lis.size(); i++) {
            String href = lis.get(i).findElement(By.tagName("a")).getAttribute("href");
            WebDriver driver = LaunchChrome.launch(href, path);
            /*List<WebElement> trs = driver.findElement(By.className("re_lttab")).findElements(By.tagName("tr"));
            ProductAcquire productAcquire = new ProductAcquire();
            productAcquire.setCnName(trs.get(1).findElements(By.tagName("td")).get(2).getText().split(" ")[0].trim());
            productAcquire.setEnName(trs.get(2).findElements(By.tagName("td")).get(1).getText().split(" ")[0].trim());
            productAcquire.setCas(trs.get(3).findElements(By.tagName("td")).get(1).getText().trim());
            productAcquire.setFormula(trs.get(4).findElements(By.tagName("td")).get(1).getText().trim());
            productAcquire.setFormulaWeight(trs.get(4).findElements(By.tagName("td")).get(3).getText().trim());
            productAcquires.add(productAcquire);
            Thread.sleep(3000);
            driver.quit();
            Thread.sleep(3000);*/
            WebElement reLttabb = driver.findElement(By.className("result_bor")).findElements(By.tagName("table")).get(1);
            System.out.println(reLttabb.getText());
           String parameter = reLttabb.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(2).getText().trim();
            List<WebElement> elements = reLttabb.findElement(By.className("proPkg")).findElements(By.tagName("td"));
            List<String> temp = Lists.newArrayList();
            elements.forEach(element -> temp.add(element.getText()));
            int num = temp.size() / 8;
            for (int k = 0; k < num; k++) {
                Goods goods = new Goods();
                int j = i * 8;
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

                goods.setUpdateTime(new Date());
                goods.setPublished(1);
                goods.setDel(false);
                goods.setGoodsId(0);

            }
            Thread.sleep(3000);
            driver.quit();
            Thread.sleep(3000);
        }
        productAcquires.forEach(s-> System.out.println(s));
        driver1.quit();
    }

}
