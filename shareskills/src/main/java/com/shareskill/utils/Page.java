package com.shareskill.utils;

public class Page {
    private int pageNo;
    private int pageSize=2;
    private int prePageNo;
    private int nextPageNo;
    private int total;
    private int pageTotal = 1;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Page() {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private String uri;

    public Page(int pageSize, String uri,int total) {
        this.pageSize = pageSize;
        this.uri = uri;
        this.total=total;
    }

    public int getPrePageNo() {
        return prePageNo;
    }

    public void setPrePageNo(int prePageNo) {
        this.prePageNo = prePageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", prePageNo=" + prePageNo +
                ", nextPageNo=" + nextPageNo +
                ", total=" + total +
                ", uri='" + uri + '\'' +
                '}';
    }
}
