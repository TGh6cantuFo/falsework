package com.yaowk.common.plugin;

import com.jfinal.kit.Kv;

import java.util.Map;

/**
 * @authc yaowk
 * 2017/7/3
 */
public class FindKv extends Kv {

    public static FindKv create() {
        return new FindKv();
    }

    public FindKv setTable(String table) {
        set("table", table);
        return this;
    }

    public FindKv setCondition(Map condition) {
        set("condition", condition);
        return this;
    }
}
