package com.mall.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Goods implements Serializable {
    private Integer goodsId;
    @Excel(name = "goodsNo")
    private String goodsNo;

    private Integer productId;

    private Integer brandId;

    private String specification;

    private BigDecimal weight;

    private String parameter;

    private BigDecimal price;

    private BigDecimal costPrice;

    private BigDecimal realPrice;

    private Integer discountNum;

    private BigDecimal discountPrice;

    private Integer published;

    private Boolean del;

    private Integer oldGoodsId;

    private String sapCode;

    private Integer sales;

    private String unit;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
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

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Integer getOldGoodsId() {
        return oldGoodsId;
    }

    public void setOldGoodsId(Integer oldGoodsId) {
        this.oldGoodsId = oldGoodsId;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode == null ? null : sapCode.trim();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsNo='" + goodsNo + '\'' +
                ", productId=" + productId +
                ", brandId=" + brandId +
                ", specification='" + specification + '\'' +
                ", weight=" + weight +
                ", parameter='" + parameter + '\'' +
                ", price=" + price +
                ", costPrice=" + costPrice +
                ", realPrice=" + realPrice +
                ", discountNum=" + discountNum +
                ", discountPrice=" + discountPrice +
                ", published=" + published +
                ", del=" + del +
                ", oldGoodsId=" + oldGoodsId +
                ", sapCode='" + sapCode + '\'' +
                ", sales=" + sales +
                ", unit='" + unit + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}