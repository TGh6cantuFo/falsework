package com.yaowk.system.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.authc.credential.PasswordService;

/**
 * @authc yaowk
 * 2017/7/16
 */
public class PasswordKit {

    private static final PasswordService passwordService = new DefaultPasswordService();

    private static final PasswordMatcher passwordMatcher = new PasswordMatcher();

    static {
        passwordMatcher.setPasswordService(passwordService);
    }

    public static String encrypt(String password) {
        return passwordService.encryptPassword(password);
    }

    public static boolean matcher(String submitPassword, String infoPassword) {
        return passwordMatcher.doCredentialsMatch(new UsernamePasswordToken(null, submitPassword), new SimpleAuthenticationInfo(null, infoPassword));
    }

    public static void main(String[] args){
        System.out.println(PasswordKit.encrypt("123456"));
    }
}
