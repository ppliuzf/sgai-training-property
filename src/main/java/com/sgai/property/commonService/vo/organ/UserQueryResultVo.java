package com.sgai.property.commonService.vo.organ;

import java.util.List;

/**
 * description:
 * Created by llh on 2017/8/15.
 */
public class UserQueryResultVo {

   private int totalRecords;
   private int totalPages;
   private int pageNum;
   private int pageSize;
   private List<OrganUserVo> list;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrganUserVo> getList() {
        return list;
    }

    public void setList(List<OrganUserVo> list) {
        this.list = list;
    }
}
