package com.rk.dto.request;

/**
 * Created by Qin_Yikai on 2018-09-20.
 */
public class PageRequest {
    private int page;
    private int limit;
    private String queryString;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public PageRequest(int page, int limit, String queryString) {
        this.page = page;
        this.limit = limit;
/*        if (queryString == null || queryString.length() == 0)
            this.queryString = null;
        else*/
        this.queryString = queryString;
    }

    public PageRequest(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public PageRequest() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
