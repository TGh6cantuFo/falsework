package com.yaowk.user.common;

import com.yaowk.common.generator.AbstractGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * User模块Model生成类
 *
 * @authc yaowk
 * 2017/6/27
 */
public class Generator extends AbstractGenerator {

    public Generator(List tableNames, String tableName) {
        super(tableNames, tableName);
    }

    public static void main(String[] args) {

        // 需要生成model的表名
        List tableNames = Arrays.asList(new String[] { "sys_user", "sys_menu", "sys_role", "sys_role_menu", "sys_user_role", "sys_platform", "sys_user_platform" });

        new Generator(tableNames, "sys_").generator();
    }
}
