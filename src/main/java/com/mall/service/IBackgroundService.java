package com.mall.service;

import com.mall.entity.BackgroundUser;

/**
 * Created by huangtao on 2018/6/14
 */
public interface IBackgroundService {
    BackgroundUser getBackgroundUserByUserName(String usernmae);
}
