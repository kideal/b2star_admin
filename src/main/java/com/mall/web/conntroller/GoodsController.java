package com.mall.web.conntroller;

import com.mall.dto.GoodsDto;
import com.mall.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by huangtao on 2018/6/28
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("goods")
    public String goods() {
        return "goods/goods";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsDto> upLoad(MultipartFile file) {
        List<GoodsDto> upload = goodsService.upload(file);
        return upload;
    }
}
