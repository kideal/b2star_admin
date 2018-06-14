package com.mall.service.impl;

import com.mall.dao.BackgroundUserMapper;
import com.mall.entity.BackgroundUser;
import com.mall.entity.BackgroundUserExample;
import com.mall.service.IBackgroundService;
import com.mall.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangtao on 2018/6/14
 */
@Service("backgroundService")
public class BackgroundServiceImpl implements IBackgroundService {
    @Autowired
    private BackgroundUserMapper backgroundUserMapper;

    @Override
    public BackgroundUser getBackgroundUserByUserName(String usernmae) {
        BackgroundUserExample backgroundUserExample = new BackgroundUserExample();
        backgroundUserExample.createCriteria().andUsernameEqualTo(usernmae);
        List<BackgroundUser> backgroundUsers = backgroundUserMapper.selectByExample(backgroundUserExample);
        if (ObjectUtil.isNotEmpty(backgroundUsers)) {
            return backgroundUsers.get(0);
        }
        return null;
    }
}
