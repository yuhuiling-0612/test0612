package com.example.demo.util;

import com.github.pagehelper.PageInfo;

public class Params {
    private Integer pageNum;
    private Integer pageSize;
    private PageInfo pageInfo;
    private String moHu;

    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getMoHu() {
        return moHu;
    }

    public void setMoHu(String moHu) {
        this.moHu = moHu;
    }
}
