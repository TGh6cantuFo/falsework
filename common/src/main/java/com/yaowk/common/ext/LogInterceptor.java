package com.yaowk.common.ext;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.log.Log;

/**
 * @authc yaowk
 * 2017/7/17
 */
public class LogInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        Log.getLog("").info("");
    }
}
