package com.arterp.customer.entity.enums;

public enum BusinessStatus {
    NORMAL("正常"),
    ABNORMAL("异常"),
    TERMINATED("终止"),
    COMPLETED("完成");

    private final String description;

    BusinessStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 