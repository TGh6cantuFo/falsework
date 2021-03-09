package com.yaowk.util;

import com.jfinal.template.IStringSource;
import com.yaowk.constant.Constant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jie on 2017/4/21.
 * 扫描jar中的sql文件
 */
public class ScanJarStringSource implements IStringSource {
    private String fileName;
    private String encoding;

    public ScanJarStringSource(String fileName) {
        this.fileName = fileName;
        this.encoding = Constant.CHARSET;
    }

    public ScanJarStringSource(String fileName, String encoding) {
        this.fileName = fileName;
        this.encoding = encoding;
    }

    public boolean isModified() {
        return true;
    }

    public String getKey() {
        return this.fileName;
    }

    public StringBuilder getContent() {
        return loadFile(fileName, encoding);
    }

    public String getEncoding() {
        return this.encoding;
    }

    private String buildFinalFileName(String fileName) {
        char firstChar = fileName.charAt(0);
        String finalFileName;
        if (firstChar != 47 && firstChar != 92) {
            finalFileName = File.separator + fileName;
        }
        else {
            finalFileName = fileName;
        }

        return finalFileName;
    }

    private static StringBuilder loadFile(String fileName, String encoding) {
        StringBuilder out = new StringBuilder();
        InputStream inputStream = com.jfinal.template.FileStringSource.class.getClassLoader().getResourceAsStream(fileName);
        byte[] b = new byte[4096];
        try {
            for (int n; (n = inputStream.read(b)) != -1; ) {
                out.append(new String(b, 0, n, encoding));
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error loading sql file.", e);
        }
        finally {
            if (inputStream != null) try {
                inputStream.close();
            }
            catch (IOException e) {
                com.jfinal.kit.LogKit.error(e.getMessage(), e);
            }
        }
        return out;
    }
}
