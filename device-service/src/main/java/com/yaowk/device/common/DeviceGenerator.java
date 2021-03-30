package com.yaowk.device.common;

import com.yaowk.common.generator.AbstractGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class DeviceGenerator extends AbstractGenerator {
    public DeviceGenerator(List tableNames, String tableNamePrefix) {
        super(tableNames, tableNamePrefix);
    }

    public static void main(String[] args){
        // 需要生成model的表名
        List tableNames = Arrays.asList(new String[] { "d_device","d_goods","d_replenishment_log" });

        new DeviceGenerator(tableNames, "d_").generator();
    }
}
