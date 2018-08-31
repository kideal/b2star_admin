package com.mall;

import com.mall.service.IAcquireDataService;
import com.mall.service.ICrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
@WebAppConfiguration
public class WebApplicationTests {
    @Autowired
    private ICrawlerService updateService;

    @Autowired
    private IAcquireDataService acquireDataService;


    @Test
    public void test1() {



    }

    @Test
    public void test2() {

    }

}
