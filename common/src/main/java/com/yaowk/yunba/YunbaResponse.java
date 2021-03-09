package com.yaowk.yunba;

import java.util.Map;

/**
 * 云巴ResultFul api 返回值
 *
 * @authc yaowk
 * 2017/5/9
 */
public class YunbaResponse {
    private Integer status;
    private String messageId;
    private Map<String, Result> results;

    public void setResults(Map<String, Result> results) {
        this.results = results;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessageId() {
        return messageId;
    }

    public Map<String, Result> getResults() {
        return results;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public class Result {
        private Integer status;
        private String error;
        private String messageId;
        private String alias;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }
}
