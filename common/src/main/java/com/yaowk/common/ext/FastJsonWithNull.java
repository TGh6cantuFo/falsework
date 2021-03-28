package com.yaowk.common.ext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfinal.json.FastJson;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class FastJsonWithNull extends FastJson {
    @Override
    public String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }
}
