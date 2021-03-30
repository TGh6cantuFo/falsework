package com.yaowk.user.common;

import com.yaowk.common.generator.AbstractGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class UserGenerator extends AbstractGenerator {
    public UserGenerator(List tableNames, String tableNamePrefix) {
        super(tableNames, tableNamePrefix);
    }

    public static void main(String[] args) {
        // 需要生成model的表名
        List tableNames = Arrays.asList(new String[] { "t_user" });

        new UserGenerator(tableNames, "t_").generator();
    }
}
