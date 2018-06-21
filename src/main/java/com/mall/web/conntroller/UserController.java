package com.mall.web.conntroller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mall.entity.User;
import com.mall.service.IUserService;
import com.mall.utils.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by huangtao on 2018/6/12
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("")
    public String index() {
        return "user/user-list";
    }

    @RequestMapping(value = "queryUserList", method = RequestMethod.GET)
    public void queryUserList(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("limit"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String userName = request.getParameter("userName");
        String mobile = request.getParameter("mobile");
        HashMap<String, String> params = new HashMap();
        params.put("page", page);
        params.put("rows", rows);
        params.put("userName", userName);
        params.put("mobile", mobile);
        PageInfo<User> pageInfo = userService.getUsers(params);
        JSONObject jo = new JSONObject();
        jo.put("data", pageInfo.getList());
        jo.put("count", pageInfo.getTotal());
        jo.put("code", 0);
        jo.put("msg", "");
       /* jo.put("total", pageInfo.getPages());//总页数
        jo.put("records", pageInfo.getTotal());//查询出的总记录数*/
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    @RequestMapping("userInfo")
    public String userInfo() {
        return "user/userInfo";
    }

    @RequestMapping("changepwd")
    public String changepwd() {
        return "user/changepwd";
    }
}
