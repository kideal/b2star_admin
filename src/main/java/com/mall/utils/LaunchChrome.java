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
    public static WebDriver launch(String url) throws Exception {
        String path = ResourceUtils.getURL("classpath:static/selenium/chromedriver.exe").getPath();
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
        WebDriver driver = launch("https://www.sigmaaldrich.com/catalog/product/aldrich/80723?lang=zh&region=CN");
        List<WebElement> skus = driver.findElements(By.className("sku"));
        for (int i = 0; i <skus.size() ; i++) {
            System.out.println(skus.get(i).getText());
        }
        driver.quit();
    }

}
