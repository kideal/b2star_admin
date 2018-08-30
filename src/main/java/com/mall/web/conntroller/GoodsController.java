package com.mall.web.conntroller;

import com.mall.common.observer.impl.Notice;
import com.mall.common.observer.impl.Receiver;
import com.mall.dto.GoodsDto;
import com.mall.entity.Goods;
import com.mall.service.IGoodsService;
import com.mall.service.IUpdateService;
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
    @Autowired
    private IUpdateService updateService;

    private Notice notice = Notice.getNotice();

    Receiver receiver = new Receiver("receiver1");

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

    @RequestMapping("update")
    public String update() {
        return "goods/update";
    }

    @RequestMapping(value = "start", method = RequestMethod.GET)
    @ResponseBody
    public void acquireStart(String type, Integer brandId) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                switch (type) {
                    case "all":
                        break;
                    case "notAll":
                        updateService.UpdateService(brandId);
                        break;
                }
            }
        };
        notice.registerObserver(receiver);
        notice.setInfo("任务开始了，正在执行中！");
        thread.start();
    }

    @RequestMapping("current")
    @ResponseBody
    public String currentProcess() {
        Object object = receiver.getObject();
        if (object != null) {
            return (String) object;
        }
        return "当前没有任务执行，请选择任务！";
    }
}
