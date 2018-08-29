package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.observer.impl.Notice;
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

import java.util.Random;

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

    @Override
    public void UpdateService(Integer brandId) {
        logger.info("进程开始了。。。");
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andBrandIdEqualTo(brandId);
        PageHelper.startPage(1, 20);
        PageInfo<Goods> pageInfo;
        pageInfo = new PageInfo<>(goodsMapper.selectByExample(goodsExample));
        int pageNum = pageInfo.getPages();
        int count = 0;
        for (int i = 1; i < pageNum + 1; i++) {
            if (i != 1) {
                PageHelper.startPage(i, 20);
                pageInfo = new PageInfo<>(goodsMapper.selectByExample(goodsExample));
            }
            for (Goods goods : pageInfo.getList()) {
                Goods goodsTemp = null;
                switch (brandId) {
                    case 319:
                        goodsTemp = acquireDataService.EnergyAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo(), goods.getSpecification());
                        break;
                    case 340:
                        goodsTemp = acquireDataService.NJAcquireDate(goods.getGoodsId(), goods.getBrandId(), goods.getGoodsNo());
                        break;
                }
                if (ObjectUtil.isNotEmpty(goodsTemp)) {
                    count++;
                    goodsTemp.setGoodsId(count);
                    notice.setInfo(goodsTemp);
                    logger.info("当前进程-货号：{}，价格{}，售价{}，成本{};统计-{}", goodsTemp.getGoodsNo(), goodsTemp.getPrice(), goodsTemp.getRealPrice(), goodsTemp.getCostPrice(), count);
                } else {
                    logger.info("当前进程-货号：{};失败", goods.getGoodsNo());
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
    }
}
