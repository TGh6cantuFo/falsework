package com.yaowk.user.shiro;

/**
 * @authc yaowk
 * 2017/7/4
 */
public class UserInfo {

    public Integer id;
    public String username;

    public UserInfo() {
    }

    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
