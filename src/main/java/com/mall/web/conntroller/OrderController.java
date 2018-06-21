package com.mall.web.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangtao on 2018/6/21
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("")
    public String list() {
        return "order/order-list";
    }
}
