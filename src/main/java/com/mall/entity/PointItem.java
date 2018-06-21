package com.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PointItem implements Serializable {
    private Integer pointItemId;

    private String name;

    private Integer point;

    private String typeCode;

    private BigDecimal price;

    private Integer img2Id;

    private Integer imgId;

    private Integer status;

    private Integer num;

    private Date createDate;

    private Integer createUserId;

    private Date updateDate;

    private Integer updateUserId;

    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getPointItemId() {
        return pointItemId;
    }

    public void setPointItemId(Integer pointItemId) {
        this.pointItemId = pointItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getImg2Id() {
        return img2Id;
    }

    public void setImg2Id(Integer img2Id) {
        this.img2Id = img2Id;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}