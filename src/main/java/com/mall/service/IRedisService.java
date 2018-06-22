package com.mall.service;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangtao on 2018/6/22
 */
public interface IRedisService {
    void setKeyValue(String key, String value);

    void setKeyValueWithTimeout(String key, String value, Integer timeout, TimeUnit timeUnit);

    Long getExpire(String key);

    String getValueByKey(String key);

    Boolean checkCodeTimeout(String key, Integer timeLeft);

    Boolean checkMsgCode(String smsKey, String smsCode);
}
