package com.yaowk.util;

import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;

/**
 * 国际化工具类
 * Created by yaowk on 2017/4/15.
 */
public class I18nKit {

    /**
     * 获取国际化资源
     *
     * @param key        关键字
     * @param baseName   文件名
     * @param controller 当前controller
     * @return
     */
    public static String get(String key, String baseName, Controller controller) {
        Res res = getRes(baseName, controller);
        return res.get(key);
    }

    /**
     * 获取格式化国际化资源
     *
     * @param key        关键字
     * @param baseName   文件名
     * @param controller 当前controller
     * @param objects    格式化参数
     * @return
     */
    public static String format(String key, String baseName, Controller controller, Object... objects) {
        Res res = getRes(baseName, controller);
        return res.format(key, objects);
    }

    private static Res getRes(String baseName, Controller controller) {
        String locale = controller.getCookie(PropKit.use("common.properties").get("I18n.localeParaName"));
        if (StrKit.isBlank(locale))
            locale = PropKit.use("common.properties").get("I18n.defaultLocale");
        return I18n.use(baseName, locale);
    }
}
