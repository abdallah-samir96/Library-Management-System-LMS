package com.lms.app.model.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LMSResponse <T>{
    private T data;
    private Long totalCounts = null;
    private Integer page;
    private Integer pageSize;


    public T getData() {
        return data;
    }

    public LMSResponse <T> setData(T data) {
        this.data = data;
        return this;
    }

    public Long getTotalCounts() {
        return totalCounts;
    }

    public LMSResponse <T> setTotalCounts(long totalCounts) {
        this.totalCounts = totalCounts;
        return this;
    }
    public LMSResponse<T> setPage(Integer page) {
        this.page = page;
        return this;
    }
    public LMSResponse<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public LMSResponse<T> build() {
        return this;
    }
}
