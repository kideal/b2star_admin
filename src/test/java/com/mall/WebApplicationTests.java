package com.mall;

import com.mall.dao.consumized.GoodsServiceMapper;
import com.mall.entity.Goods;
import com.mall.service.IAcquireDataService;
import com.mall.service.IUpdateService;
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
	private IUpdateService updateService;

	@Autowired
	private IAcquireDataService acquireDataService;

	@Autowired
	private GoodsServiceMapper goodsServiceMapper;

	@Test
	public void test1() {

		Goods goods = goodsServiceMapper.selectByPrimaryKey(1);
		System.out.println(goods);

	}

	@Test
	public void test2() {
		acquireDataService.EnergyAcquireDate(11335, 319, "a0101450250-25.00g", "25.00g");


	}

}
