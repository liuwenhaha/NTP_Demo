package com.alick.ntp_test;

/**
 * Created by Alick on 2016/10/31.
 */

public class TimeBean {
    private String serviceHost;
    private long serviceTime;
    private long localTime;

    public TimeBean() {
    }

    public TimeBean(String serviceHost, long serviceTime, long localTime) {
        this.serviceHost = serviceHost;
        this.serviceTime = serviceTime;
        this.localTime = localTime;
    }

    public long getLocalTime() {
        return localTime;
    }

    public void setLocalTime(long localTime) {
        this.localTime = localTime;
    }

    public String getServiceHost() {
        return serviceHost;
    }

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }
}
