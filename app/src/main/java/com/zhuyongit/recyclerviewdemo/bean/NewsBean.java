package com.zhuyongit.recyclerviewdemo.bean;

import java.util.List;

/**
 * Created by zeno on 2015/11/5.
 */
public class NewsBean {

    public NewsBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private String title ;
    private String content ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
