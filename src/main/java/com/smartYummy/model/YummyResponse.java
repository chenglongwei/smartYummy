package com.smartYummy.model;

import java.util.Date;

/**
 * Created by chenglongwei on 5/11/16.
 */
public class YummyResponse {
    private String status;
    private Date earlistTime;
    private String error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEarlistTime() {
        return earlistTime;
    }

    public void setEarlistTime(Date earlistTime) {
        this.earlistTime = earlistTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
