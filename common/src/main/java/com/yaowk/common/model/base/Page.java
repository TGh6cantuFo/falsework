package com.yaowk.common.model.base;

import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Record;

/**
 * @authc yaowk
 * 2017/4/25
 */
public class Page {
    private int pageNumber = 1;
    private int pageSize = Integer.MAX_VALUE;

    public int getPageNumber() {

        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Record toRecord() {
        throw new ActiveRecordException("Record Without Override");
    }
}
