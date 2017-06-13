package com.maplechen.mu.Domain;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class NewInfo {
    /**
     * {
     "code": 200,
     "msg": "success",
     "newslist": [
                 {
                 "ctime": "2015-07-07 00:00",
                 "description": "猎奇奇闻",
                 "picUrl": "http://img521.lieqi.com/upload/picture/39/11423.jpg",
                 "title": "带诅咒的红色汽车 史上死1000多万人",
                 "url": "http://www.lieqi.com/read/4/11423/"
     */
    public String code;
    public String msg;
    public List<NewList> newslist;
    public class NewList {
        public String ctime;
        public String description;
        public String picUrl;
        public String title;
        public String url;
    }

}
