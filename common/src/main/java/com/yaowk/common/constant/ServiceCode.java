package com.yaowk.common.constant;

/**
 * 业务代码
 *
 * @authc yaowk
 * 2017/4/29
 */
public interface ServiceCode {
    public static final String NORMAL = "00000";
    public static final String SUCCESS = "00050"; // 操作成功
    public static final String NO_LOGIN = "10000"; // 未登录
    public static final String NO_PERMISSION = "20000"; // 没有权限
    public static final String FAIL = "40000"; // 操作失败
    public static final String UPLOAD_FAIL = "40010"; // 上传操作失败
    public static final String FIND_FAIL = "00020"; // 查找失败，没有找到相关记录
    public static final String UPDATE_FAIL = "40030"; // 更新操作失败
    public static final String UPDATE_ID_FAIL = "40031"; // 更新失败，缺少参数id
    public static final String UPDATE_FIND_FAIL = "40032"; // 更新失败，没有这条记录
    public static final String UPDATE_PARAM_FAIL = "40033"; // 更新失败，传送的参数异常
    public static final String ADD_FAIL = "40040"; // 添加失败
    public static final String ADD_FAIL_NO_TYPE = "40041"; // 添加失败,没有相应的类型
    public static final String ADD_USERNAME_EXIST = "40050"; // 用户名已经存在
    public static final String LOGIN_USERNAME_EMPTY = "50050"; // 用户不存在
    public static final String PASSWORD_ERROR = "50100"; // 密码错误
    public static final String ERROR = "99999"; // 异常
}
