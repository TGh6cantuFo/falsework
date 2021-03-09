package com.yaowk.util;

import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by jie on 2017/4/9.
 * 文件上传
 */
public class FileUploadKit {
    /**
     * 文件重命名方法
     *
     * @param uploadFile
     * @param username
     * @return
     * @throws IOException
     */
    public static String mv(UploadFile uploadFile, String username) throws IOException {
        if (null != uploadFile && null != uploadFile.getFile()) {
            File file = uploadFile.getFile();
            String uploadPath = PropKit.get("uploadPath");
            String fileName = uploadPath + file.getName();
            return fileName;
        }
        return null;
    }
}
