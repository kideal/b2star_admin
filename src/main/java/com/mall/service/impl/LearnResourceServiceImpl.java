package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.dao.LearnResourceMapper;
import com.mall.entity.LearnResource;
import com.mall.entity.LearnResourceExample;
import com.mall.service.ILearnResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huangtao on 2018/6/12
 */
@Service("LearnResourceService")
public class LearnResourceServiceImpl implements ILearnResourceService {
    @Autowired
    private LearnResourceMapper learnResourceMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PageInfo<LearnResource> queryLearnResouceList(HashMap<String,String> parm) {
        PageHelper.startPage(Integer.valueOf(parm.get("page")), Integer.valueOf(parm.get("rows")));
        LearnResourceExample learnResourceExample = new LearnResourceExample();
        learnResourceExample.createCriteria().andIdIsNotNull();
        List<LearnResource> learnResources = learnResourceMapper.selectByExample(learnResourceExample);
        return new PageInfo<>(learnResources);
    }
}
