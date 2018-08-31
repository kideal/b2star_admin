package com.mall;

import com.mall.dao.GoodsAcquireMapper;
import com.mall.entity.Goods;
import com.mall.service.IAcquireDataService;
import com.mall.service.IUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
public class WebApplicationTests {
    @Autowired
    private IUpdateService updateService;

    @Autowired
    private IAcquireDataService acquireDataService;


    @Test
    public void test1() {



    }

    @Test
    public void test2() {

    }

}
