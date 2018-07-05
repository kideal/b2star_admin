package com.mall;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.mall.service.IOrderService;
import com.mall.service.IRowReader;
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
	private IOrderService orderService;
	@Test
	public void contextLoads()throws Exception {
		ExcelReaderUtil.readExcel("f:\\3.xlsx");
	}

	@Test
	public void test1() {
		GoodsOptionUtil.test();

	}

}
