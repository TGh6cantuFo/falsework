package com.yaowk.common.kit;

import com.jfinal.plugin.ehcache.CacheKit;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yaowk.common.constant.CacheConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库缓存工具类
 *
 * @authc yaowk
 * 2017/7/4
 */
public class DbCacheKit {

    public static void removeCache(String key) {
        List<String> keys = CacheKit.get(CacheConstant.DB, CacheConstant.KEY);
        keys.remove(key);
        CacheKit.remove(CacheConstant.DB, key);
    }

    public static void removeCacheStarWith(String starWith) {
        List<String> keys = CacheKit.get(CacheConstant.DB, CacheConstant.KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            List<String> forKeys = new ArrayList<>(keys);
            for (String key : forKeys) {
                if (key.startsWith(starWith)) {
                    removeCache(key);
                }
            }
        }
    }

    public static void removeCacheEndWith(String endWith) {
        List<String> keys = CacheKit.get(CacheConstant.DB, CacheConstant.KEY);
        if (CollectionUtil.isNotEmpty(keys)) {
            List<String> forKeys = new ArrayList<>(keys);
            for (String key : forKeys) {
                if (key.endsWith(endWith)) {
                    removeCache(key);
                }
            }
        }
    }

    public static void addKey(String key) {
        List<String> keys = CacheKit.get(CacheConstant.DB, CacheConstant.KEY, () -> new ArrayList());
        keys.add(key);
    }
}
