package com.travis.bean;

/**
 * 分页对象
 */
public class Page {

    // 总条数
    private int totalNumber;
    // 当前页数
    private int currentPage;
    // 总页数
    private int totalPage;
    // 每页显示条数
    private int pageNumber;

    /*设置好默认的当前页数和显示条数*/
    public Page() {
        this.currentPage = 1;
        this.pageNumber = 5;
    }

    private void count() {
        /*1 运算*/
        this.totalPage = this.totalNumber / this.pageNumber;
        if(this.totalNumber % this.pageNumber > 0) {
            this.totalPage++;
        }
        /*2 判断总页数不可以小于等于0，当前页数也是不可以小于等于0，当前页数必须小于等于总页数（也是最后一页）*/
        if(this.totalPage <= 0) {
            this.totalPage = 1;
        }
        if(this.currentPage > this.totalPage) {
            this.currentPage = this.totalPage;
        }
        if(this.currentPage <= 0) {
            this.currentPage = 1;
        }
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        this.count();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
