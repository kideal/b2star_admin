package com.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsPromotion implements Serializable {
    private Integer promotionId;

    private String goodsNo;

    private Integer goodsId;

    private BigDecimal price;

    private BigDecimal promPrice;

    private Date promFrom;

    private Date promTo;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromPrice() {
        return promPrice;
    }

    public void setPromPrice(BigDecimal promPrice) {
        this.promPrice = promPrice;
    }

    public Date getPromFrom() {
        return promFrom;
    }

    public void setPromFrom(Date promFrom) {
        this.promFrom = promFrom;
    }

    public Date getPromTo() {
        return promTo;
    }

    public void setPromTo(Date promTo) {
        this.promTo = promTo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}