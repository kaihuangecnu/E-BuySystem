package com.entity;

public final class PageBean {

    private int page; //当前页
    private int pageSize; //每页记录数

    public PageBean(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (page-1)*pageSize;
    }
}
