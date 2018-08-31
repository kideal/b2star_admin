package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mall.common.observer.impl.Notice;
import com.mall.common.observer.impl.Receiver;
import com.mall.dao.GoodsMapper;
import com.mall.entity.Goods;
import com.mall.entity.GoodsAcquire;
import com.mall.entity.GoodsExample;
import com.mall.service.IAcquireDataService;
import com.mall.service.ICrawlerService;
import com.mall.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangtao on 2018/8/24
 */
@Service("IUpdateService")
public class updateServiceImpl implements ICrawlerService {

    @Autowired
    private IAcquireDataService acquireDataService;
    @Autowired
    private GoodsMapper goodsMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    Notice notice = Notice.getNotice();
    static Integer count = 0;

    @Override
    public void CrawlerByDataBaseService(Integer brandId) {
        logger.info("进程开始了。。。");
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andBrandIdEqualTo(brandId);
        Receiver receiver = new Receiver("receiver2");
        notice.registerObserver(receiver);
        PageHelper.startPage(1, 50);
        PageInfo<Goods> pageInfo;
        pageInfo = new PageInfo<>(goodsMapper.selectByExample(goodsExample));
        int pageNum = pageInfo.getPages();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < pageNum + 1; i++) {
            if (i != 1) {
                PageHelper.startPage(i, 50);
                pageInfo = new PageInfo<>(goodsMapper.selectByExample(goodsExample));
            }
            List<Goods> goodsList = pageInfo.getList();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 1; j < 6; j++) {
                HashMap<String, Object> parameters = Maps.newHashMap();
                parameters.put("start", (j - 1) * 10);
                parameters.put("end", j * 10);
                parameters.put("brandId", brandId);
                parameters.put("list", goodsList);
                parameters.put("number", j);
                UpdateTask updateTask = new UpdateTask(parameters, "part");
                executorService.execute(updateTask);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        executorService.shutdown();
    }

    @Override
    public void CrawlerAllService(Integer brandId) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        switch (brandId) {
            case 319:
                for (int i = 1; i <101 ; i=i+5) {
                    for (int j = 1; j < 6; j++) {
                        String url = "https://www.energy-chemical.com/product/regentPro?o=1&pc="+(i+j-1);
                        HashMap<String, Object> parameters = Maps.newHashMap();
                        parameters.put("brandId", brandId);
                        parameters.put("url", url);
                        parameters.put("number", j);
                        UpdateTask updateTask = new UpdateTask(parameters, "all");
                        executorService.execute(updateTask);
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 390:
                break;
        }
    }

    /*
     * 参数parameters:
     * 必需带的参数：integer brandId , integer number
     * part:integer start , integer end , List(Goods) list
     * all: string url
     * */
    class UpdateTask implements Runnable {
        private HashMap<String, Object> parameters;
        private String identification;

        public UpdateTask(HashMap<String, Object> parameters, String identification) {
            this.parameters = parameters;
            this.identification = identification;
        }

        @Override
        public void run() {
            switch (identification) {
                case "all":
                    all();
                    break;
                case "part":
                    part();
                    break;
            }

        }

        private void all() {
            List<GoodsAcquire> goodsAcquires = null;
            Integer number = (Integer) parameters.get("number");
            switch ((Integer) parameters.get("brandId")) {
                case 319:
                    goodsAcquires = acquireDataService.EnergyCrawlDate(319, (String) parameters.get("url"));
                    break;
            }
            if (ObjectUtil.isNotEmpty(goodsAcquires)) {
                synchronized (count) {
                    count(goodsAcquires);
                    logger.info("{}号线程-获取数据：{}条---累计{}条", number, goodsAcquires.size(), count);
                }
            } else {
                logger.info("{}号线程-没有处理数据");
            }
        }

        private void part() {
            List<Goods> goodsList = (List<Goods>) parameters.get("list");
            Integer number = (Integer) parameters.get("number");
            for (int i = (Integer) parameters.get("start"); i < (Integer) parameters.get("end"); i++) {
                Goods goods = goodsList.get(i);
                GoodsAcquire goodsAcquire = null;
                switch ((Integer) parameters.get("brandId")) {
                    case 319:
                        goodsAcquire = acquireDataService.EnergyAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo(), goods.getSpecification());
                        break;
                    case 340:
                        goodsAcquire = acquireDataService.NJAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo());
                        break;
                }
                if (ObjectUtil.isNotEmpty(goodsAcquire)) {
                    synchronized (count) {
                        count(goodsAcquire);
                        logger.info("{}号线程-货号：{}，价格{}，售价{}，成本{};统计-{}", number, goodsAcquire.getGoodsNo(), goodsAcquire.getPrice(), goodsAcquire.getRealPrice(), goodsAcquire.getCostPrice(), count);
                    }
                } else {
                    logger.info("{}号线程-货号：{};失败", number, goods.getGoodsNo());
                }
                try {
                    Random rand = new Random();
                    int randNumber = rand.nextInt(10 - 5 + 1) + 5;
                    Thread.sleep(new Long((long) randNumber * 1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void count(GoodsAcquire goodsAcquire) {
            count++;
            String msg = "任务正在执行中！<br/>当前处理数据--货号：" + goodsAcquire.getGoodsNo() + ",价格：" + goodsAcquire.getPrice() + "<br/>累计处理数据：" + count + "条";
            notice.setInfo(msg);
        }

        private void count(List<GoodsAcquire> goodsAcquires) {
            count = count + goodsAcquires.size();
            String msg = "任务正在执行中！<br/>当前处理数据：<br/>" ;
            for (GoodsAcquire goodsAcquire : goodsAcquires) {
                msg = msg + "货号：" + goodsAcquire.getGoodsNo() + ",价格：" + goodsAcquire.getPrice() + "<br/>";
            }
            msg = msg + "累计处理数据" + count + "条";
            notice.setInfo(msg);
        }


    }

    public static void main(String[] args) {
        String s = "12345\n12345";
        System.out.println(s);
    }
}
