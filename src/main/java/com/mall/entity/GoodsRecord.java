package com.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsRecord implements Serializable {
    private Integer goodsRecordId;

    private String goodsNo;

    private String name;

    private BigDecimal costPrice;

    private BigDecimal realPrice;

    private BigDecimal prcie;

    private Integer type;

    private Integer brandId;

    private Integer identifiy;

    private String change;

    private Date dateTime;

    private static final long serialVersionUID = 1L;

    public Integer getGoodsRecordId() {
        return goodsRecordId;
    }

    public void setGoodsRecordId(Integer goodsRecordId) {
        this.goodsRecordId = goodsRecordId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getPrcie() {
        return prcie;
    }

    public void setPrcie(BigDecimal prcie) {
        this.prcie = prcie;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getIdentifiy() {
        return identifiy;
    }

    public void setIdentifiy(Integer identifiy) {
        this.identifiy = identifiy;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change == null ? null : change.trim();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}