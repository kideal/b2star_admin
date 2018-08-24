package com.mall.utils.selenium;

import com.google.common.collect.Lists;
import com.mall.entity.Goods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangtao on 2018/8/23
 */
public class LaunchChrome {
    public static WebDriver launch(String path) {
        System.setProperty("webdriver.chrome.driver", "C:\\project\\b2star_admin\\src\\main\\java\\com\\mall\\utils\\selenium\\chromedriver.exe");
        //初始化一个chrome浏览器实例，实例名称叫driver
        WebDriver driver = new ChromeDriver();
        //最大化窗口
        driver.manage().window().maximize();
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // get()打开一个站点
        driver.get(path);
        //getTitle()获取当前页面title的值
        return driver;

        //关闭并退出浏览器
        //driver.quit();

    }

    public static void main(String[] args) throws  Exception{
        /*System.out.println("开始了。。。");
        WebDriver driver = launch("http://www.nj-reagent.com/category/NS01/item-list.htm?pn=1");
        WebElement proDetailNum = driver.findElement(By.className("pro_list_1"));
        List<WebElement> trs = proDetailNum.findElements(By.tagName("tr"));
        List strings = Lists.newArrayList();
        for (int i = 1; i < trs.size(); i++) {
            List<WebElement> tds = trs.get(i).findElements(By.tagName("td"));
            String href = tds.get(0).findElement(By.tagName("a")).getAttribute("href");
            strings.add(href.split(";")[0]);
        }

        System.out.println("--------------------------");
        strings.forEach(s-> System.out.println(s));
        System.out.println("--------------------------");
        driver.quit();*/

    }

}
