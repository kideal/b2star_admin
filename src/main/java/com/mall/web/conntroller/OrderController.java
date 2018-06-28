package com.mall.web.conntroller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mall.service.IOrderService;
import com.mall.utils.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangtao on 2018/6/21
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @RequestMapping("")
    public String list() {
        return "order/order-list";
    }

    @RequestMapping("queryOrderList")
    public void queryOrderList(HttpServletRequest request, HttpServletResponse response) {
        Integer page = Integer.valueOf(request.getParameter("page"));
        Integer rows = Integer.valueOf(request.getParameter("limit"));
        Integer orderStatus = 1;
        Integer payStatus = 1;
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        PageInfo pageInfo = orderService.queryOrderList(orderStatus, payStatus,
                page, rows, start, end);
        JSONObject jo = new JSONObject();
        jo.put("data", pageInfo.getList());
        jo.put("count", pageInfo.getTotal());
        jo.put("code", 0);
        jo.put("msg", "");
        ServletUtil.createSuccessResponse(200, jo, response);
    }
}
