package com.mall.service.impl;

import com.mall.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangtao on 2018/6/22
 */
@Service("redisService")
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    @Override
    public void setKeyValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置值有过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    @Override
    public void setKeyValueWithTimeout(String key, String value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 获取值得过期时间
     *
     * @param key
     * @return
     */
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public String getValueByKey(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 判断验证码
     *
     * @param key
     * @return
     */
    @Override
    public Boolean checkCodeTimeout(String key, Integer timeLeft) {
        Long time = this.getExpire(key);
        if (time > timeLeft) {
            return false;
        }
        return true;
    }

    /**
     * 判断 smsCode 是否正确
     *
     * @return
     */
    @Override
    public Boolean checkMsgCode(String msgKey, String msgCode) {

        String code = this.getValueByKey(msgKey);

        if (code == null) {
            return false;
        }
        if (!code.equals(msgCode)) {
            return false;
        }
        redisTemplate.delete(msgKey);
        return true;
    }
}
