package com.mall.service.impl;

import com.mall.service.IGoodsService;
import com.mall.service.IRowReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by huangtao on 2018/7/5
 */
@Service("goodsUpload")
public class RowReaderImpl implements IRowReader {

    @Autowired
    private IGoodsService goodsService;

    /* 业务逻辑实现方法
     * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
     */
    public void getRows(HashMap<String, String> map) {
        // TODO Auto-generated method stub
        goodsService.test(map);
    }

}


