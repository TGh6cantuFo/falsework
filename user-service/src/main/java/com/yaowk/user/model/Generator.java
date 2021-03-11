package com.yaowk.user.model;

import com.yaowk.common.generator.AbstractGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @authc yaowk
 * 2017/6/27
 */
public class Generator extends AbstractGenerator {

    public Generator(List tableNames) {
        super(tableNames);
    }

    public static void main(String[] args) {

        // 需要生成model的表名
        List tableNames = Arrays.asList(new String[] { "sys_user", "sys_menu", "sys_role", "sys_role_menu", "sys_user_role" });

        new Generator(tableNames).generator();
    }
}
