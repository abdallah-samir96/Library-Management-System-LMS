package com.lms.app.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LMSResponse <T>{
    private T data;
    private Long totalCounts = null;

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

    public LMSResponse<T> build() {
        return this;
    }
}
