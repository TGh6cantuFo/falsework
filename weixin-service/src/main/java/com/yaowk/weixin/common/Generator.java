package com.yaowk.weixin.common;

import com.yaowk.common.generator.AbstractGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @authc yaowk
 * 2017/7/14
 */
public class Generator extends AbstractGenerator {

    public Generator(List tableNames, String tableNamePrefix) {
        super(tableNames, tableNamePrefix);
    }

    public static void main(String[] args) {

        // 需要生成model的表名
        List tableNames = Arrays.asList(new String[] { "sys_fans", "sys_action" });

        new Generator(tableNames, "sys_").generator();
    }
}
