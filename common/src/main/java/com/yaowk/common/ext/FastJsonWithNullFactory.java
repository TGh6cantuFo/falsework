package com.yaowk.common.ext;

import com.jfinal.json.FastJsonFactory;
import com.jfinal.json.Json;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class FastJsonWithNullFactory extends FastJsonFactory {
    @Override
    public Json getJson() {
        return new FastJsonWithNull();
    }
}
