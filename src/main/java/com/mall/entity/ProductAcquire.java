package com.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductAcquire implements Serializable {
    private Integer productId;

    private String cnName;

    private String cnAnotherName;

    private String enName;

    private String enAnotherName;

    private String cas;

    private String formula;

    private String formulaWeight;

    private String standard;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getCnAnotherName() {
        return cnAnotherName;
    }

    public void setCnAnotherName(String cnAnotherName) {
        this.cnAnotherName = cnAnotherName == null ? null : cnAnotherName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getEnAnotherName() {
        return enAnotherName;
    }

    public void setEnAnotherName(String enAnotherName) {
        this.enAnotherName = enAnotherName == null ? null : enAnotherName.trim();
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas == null ? null : cas.trim();
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula == null ? null : formula.trim();
    }

    public String getFormulaWeight() {
        return formulaWeight;
    }

    public void setFormulaWeight(String formulaWeight) {
        this.formulaWeight = formulaWeight == null ? null : formulaWeight.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
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

    @Override
    public String toString() {
        return "ProductAcquire{" +
                "productId=" + productId +
                ", cnName='" + cnName + '\'' +
                ", cnAnotherName='" + cnAnotherName + '\'' +
                ", enName='" + enName + '\'' +
                ", enAnotherName='" + enAnotherName + '\'' +
                ", cas='" + cas + '\'' +
                ", formula='" + formula + '\'' +
                ", formulaWeight='" + formulaWeight + '\'' +
                ", standard='" + standard + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}