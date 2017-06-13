package com.maplechen.mu.Domain;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class ImgInfo {
    /**
     * "status": true,
         "tngou": [
         {
         "count": 5532,
         "fcount": 0,
         "galleryclass": 5,
         "id": 6,
         "img": "/ext/150714/426262edf0f5975f41cf4abf8274b21b.jpg",
         "rcount": 0,
         "size": 13,
         "time": 1436875387000,
         "title": "知名嫩模莫露大尺度布条装写真"
     },
     */

    public String status;
    public String total;
    public List<Gallery>  tngou;

    public class  Gallery {
        public int id;
        public int  galleryclass ;//          图片分类
        public String title     ;//          标题
        public String img     ;//          图库封面
        public int count     ;//          访问数
        public int rcount     ;//           回复数
        public int fcount     ;//          收藏数
        public int size     ;//      图片多少张
    }
}
