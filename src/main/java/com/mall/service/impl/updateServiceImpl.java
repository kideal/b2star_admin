package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.observer.impl.Notice;
import com.mall.common.observer.impl.Receiver;
import com.mall.dao.GoodsMapper;
import com.mall.entity.Goods;
import com.mall.entity.GoodsExample;
import com.mall.service.IAcquireDataService;
import com.mall.service.IUpdateService;
import com.mall.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangtao on 2018/8/24
 */
@Service("IUpdateService")
public class updateServiceImpl implements IUpdateService {

    @Autowired
    private IAcquireDataService acquireDataService;
    @Autowired
    private GoodsMapper goodsMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    Notice notice = Notice.getNotice();
    static Integer count = 0;
    static String path = "";

    @Override
    public void UpdateService(Integer brandId) {
        logger.info("进程开始了。。。");
        try {
            path = ResourceUtils.getURL("classpath:static/selenium/chromedriver.exe").getPath();
            System.out.println(path);
        } catch (Exception e) {

        }
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andBrandIdEqualTo(brandId);
        Receiver receiver = new Receiver("receiver2");
        notice.registerObserver(receiver);
        PageHelper.startPage(1, 50);
        PageInfo<Goods> pageInfo;
        pageInfo = new PageInfo<>(goodsMapper.selectByExample(goodsExample));
        int pageNum = pageInfo.getPages();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < pageNum+1; i++) {
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
                UpdateTask updateTask = new UpdateTask((j - 1) * 10, j * 10, brandId, goodsList, j);
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

    class UpdateTask implements Runnable {
        private Integer start;
        private Integer end;
        private Integer brandId;
        private List<Goods> goodsList;
        private Integer number;


        public UpdateTask(Integer start, Integer end, Integer brandId, List<Goods> goodsList, Integer number) {
            this.start = start;
            this.end = end;
            this.brandId = brandId;
            this.goodsList = goodsList;
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                Goods goods = goodsList.get(i);
                Goods goodsTemp = null;
                switch (brandId) {
                    case 319:
                        goodsTemp = acquireDataService.EnergyAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo(), goods.getSpecification(), path);
                        break;
                    case 340:
                        goodsTemp = acquireDataService.NJAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo(), path);
                        break;
                }
                if (ObjectUtil.isNotEmpty(goodsTemp)) {
                    synchronized (count) {
                        count(goodsTemp);
                        logger.info("{}号线程-货号：{}，价格{}，售价{}，成本{};统计-{}", number, goodsTemp.getGoodsNo(), goodsTemp.getPrice(), goodsTemp.getRealPrice(), goodsTemp.getCostPrice(), count);
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

        private void count(Goods goods) {
            count++;
            goods.setGoodsId(count);
            notice.setInfo(goods);
        }
    }

}
