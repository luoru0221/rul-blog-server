package com.rul.blog.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis操作工具类
 *
 * @author RuL
 */
@Component
public final class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存有效时间
     *
     * @param key  key
     * @param time 有效时间（s）
     * @return 设置成功与否
     */
    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }


    /**
     * 获取缓存失效时间
     *
     * @param key key
     * @return 缓存失效时间（s）
     */
    public long getExpire(String key) {
        if (key == null) {
            return -1;
        }
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return key存在与否
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param keys key
     */
    @SuppressWarnings("unchecked")
    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /*================================String=================================*/

    /**
     * 从缓存中获取值
     *
     * @param key key
     * @return value
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 往缓存中存入数据
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 往缓存中存入数据并设置有效时间
     *
     * @param key   key
     * @param value value
     * @param time  有效时间（s）
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /*================================Map=================================*/

    /**
     * 获取field对应的值
     *
     * @param key   key
     * @param field field
     * @return value
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 设置field对应的值
     *
     * @param key   key
     * @param field field
     * @param value value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }
    

    /**
     * 获取key对应的所有键值对
     *
     * @param key key
     * @return key对应的map
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置key对应的所有键值对
     *
     * @param key   key
     * @param value value
     */
    public void hmset(String key, Map<Object, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 设置key对应的键值对并设置有效时间
     *
     * @param key   key
     * @param value value
     * @param time  有效时间（s）
     */
    public void hmset(String key, Map<Object, Object> value, long time) {
        hmset(key, value);
        if (time > 0) {
            expire(key, time);
        }
    }

    /**
     * 删除fields对应的值
     *
     * @param key    key
     * @param fields fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 判断key,field是否存在
     *
     * @param key   key
     * @param field field
     * @return key, field存在与否
     */
    public boolean hHasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

}
