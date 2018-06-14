package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.entity.LearnResource;

import java.util.HashMap;

/**
 * Created by huangtao on 2018/6/12
 */
public interface ILearnResourceService {
    PageInfo<LearnResource> queryLearnResouceList(HashMap<String,String> parm);
}
