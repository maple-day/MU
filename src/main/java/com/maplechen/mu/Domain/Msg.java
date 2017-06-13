package com.maplechen.mu.Domain;

/**
 * Created by Administrator on 2017/4/20.
 */
public class Msg {
    public Msg(String msg, String date, String url) {
        this.msg = msg;
        this.date = date;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String msg;
    public String date;
    public String url;
}
