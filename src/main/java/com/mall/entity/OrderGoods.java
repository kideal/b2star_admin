package com.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoods implements Serializable {
    private Integer orderGoodsId;

    private Integer orderId;

    private Integer goodsId;

    private String goodsNo;

    private Integer productId;

    private Integer productImgId;

    private String productName;

    private Integer brandId;

    private String brand;

    private String specification;

    private String parameter;

    private BigDecimal weight;

    private BigDecimal price;

    private BigDecimal costPrice;

    private BigDecimal realPrice;

    private Integer discountNum;

    private BigDecimal discountPrice;

    private BigDecimal goodsPrice;

    private Integer goodsAmount;

    private BigDecimal confirmPrice;

    private String keywords;

    private String productTypeCode;

    private Boolean goodsDel;

    private String cas;

    private String note;

    private BigDecimal sapPrice;

    private static final long serialVersionUID = 1L;

    public Integer getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Integer orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Integer productImgId) {
        this.productImgId = productImgId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Integer getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(Integer discountNum) {
        this.discountNum = discountNum;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getConfirmPrice() {
        return confirmPrice;
    }

    public void setConfirmPrice(BigDecimal confirmPrice) {
        this.confirmPrice = confirmPrice;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode == null ? null : productTypeCode.trim();
    }

    public Boolean getGoodsDel() {
        return goodsDel;
    }

    public void setGoodsDel(Boolean goodsDel) {
        this.goodsDel = goodsDel;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas == null ? null : cas.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public BigDecimal getSapPrice() {
        return sapPrice;
    }

    public void setSapPrice(BigDecimal sapPrice) {
        this.sapPrice = sapPrice;
    }
}