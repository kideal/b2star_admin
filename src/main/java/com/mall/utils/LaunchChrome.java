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
       /* List<ProductAcquire> productAcquires = Lists.newArrayList();
        for (int i = 0; i < lis.size(); i++) {
            String href = lis.get(i).findElement(By.tagName("a")).getAttribute("href");
            WebDriver driver = LaunchChrome.launch(href, path);
            List<WebElement> trs = driver.findElement(By.className("re_lttab")).findElements(By.tagName("tr"));
            ProductAcquire productAcquire = new ProductAcquire();
            productAcquire.setCnName(trs.get(1).findElements(By.tagName("td")).get(2).getText().split(" ")[0].trim());
            productAcquire.setEnName(trs.get(2).findElements(By.tagName("td")).get(1).getText().split(" ")[0].trim());
            productAcquire.setCas(trs.get(3).findElements(By.tagName("td")).get(1).getText().trim());
            productAcquire.setFormula(trs.get(4).findElements(By.tagName("td")).get(1).getText().trim());
            productAcquire.setFormulaWeight(trs.get(4).findElements(By.tagName("td")).get(3).getText().trim());
            productAcquires.add(productAcquire);
            Thread.sleep(3000);
            driver.quit();
            Thread.sleep(3000);
        }
        productAcquires.forEach(s-> System.out.println(s));
        driver1.quit();*/
    }

}
