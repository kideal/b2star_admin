package com.mall.web.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangtao on 2018/6/12
 */
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("main")
    public String main() {
        return "main";
    }
}
