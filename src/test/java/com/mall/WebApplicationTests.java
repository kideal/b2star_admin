package com.mall;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.mall.service.IAcquireDataService;
import com.mall.service.IOrderService;
import com.mall.service.IRowReader;
import com.mall.service.IUpdateService;
import com.mall.service.impl.RowReaderImpl;
import com.mall.utils.Excel2007Reader;
import com.mall.utils.ExcelReaderUtil;
import com.mall.utils.GoodsOptionUtil;
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

	@Test
	public void test1() {

		updateService.NJUpdateService();

	}

	@Test
	public void test2() {

		acquireDataService.NJAcquireDate("C0680670123", 834784);

	}

}
