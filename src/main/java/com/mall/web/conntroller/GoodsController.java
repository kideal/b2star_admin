package com.mall.web.conntroller;

import com.mall.common.observer.impl.Notice;
import com.mall.common.observer.impl.Receiver;
import com.mall.dto.GoodsDto;
import com.mall.service.IGoodsService;
import com.mall.service.ICrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by huangtao on 2018/6/28
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ICrawlerService updateService;

    private Notice notice = Notice.getNotice();

    Receiver receiver = new Receiver("receiver1");

    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

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
    public String acquireStart(String type, Integer brandId) {
        int activeCount = executor.getActiveCount();
        if (activeCount > 0) {
            return "当前进程已满，请稍后重试！";
        } else {
            executor.submit(new task(type, brandId));
            notice.registerObserver(receiver);
            notice.setInfo("任务开始了，正在执行中！");
            return null;
        }
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

    class task implements Runnable {
        private String type;
        private Integer brandId;

        public task(String type, Integer brandId) {
            this.type = type;
            this.brandId = brandId;
        }

        @Override
        public void run() {
            switch (type) {
                case "all":
                    updateService.CrawlerAllService(brandId);
                    break;
                case "part":
                    updateService.CrawlerByDataBaseService(brandId);
                    break;
            }
        }
    }
}
