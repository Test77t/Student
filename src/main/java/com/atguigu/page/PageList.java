package com.atguigu.page;

import org.springframework.stereotype.Component;

@Component
public class PageList {
    private int page;//显示当前页面
    private int rows;
    private int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        this.offset = (page-1)*rows;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (page-1)*rows;
    }
}
