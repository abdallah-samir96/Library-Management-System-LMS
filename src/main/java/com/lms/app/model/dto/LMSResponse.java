package com.lms.app.model.dto;

public class LMSResponse <T>{
    private T data;
    private long totalCounts = 0;

    public T getData() {
        return data;
    }

    public LMSResponse <T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getTotalCounts() {
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
