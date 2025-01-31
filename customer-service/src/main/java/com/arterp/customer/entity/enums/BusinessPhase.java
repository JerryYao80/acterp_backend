package com.arterp.customer.entity.enums;

public enum BusinessPhase {
    // 获客阶段
    ADVERTISING("广告发布"),
    OFFLINE_CONTACT("线下联络"),
    REFERRAL("裂变促交"),
    PACKAGE_DESIGN("套餐拟定"),
    CONTRACT_SIGNING("合同签约"),

    // 服务阶段
    IVF("体外受精"),
    EMBRYO_TRANSFER("胚胎移植"),
    PREGNANCY_CARE("孕期护理"),
    DELIVERY("分娩出生"),
    PATERNITY_TEST("亲子鉴定"),
    OVERSEAS_CARE("出境陪护"),
    IMMIGRATION_SETTLEMENT("入境落户");

    private final String description;

    BusinessPhase(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 