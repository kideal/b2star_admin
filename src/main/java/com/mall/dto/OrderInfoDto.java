package com.mall.dto;

import com.mall.entity.*;

import java.util.List;

/**
 * Created by huangtao on 2018/6/22
 */
public class OrderInfoDto extends Orders {
    private String username;
    private String real_name;
    private String organizationName;
    private OrderAddress orderAddress;
    private String contractBuyer;
    private Payment payment;
    private List<OrderGoods> orderGoods;
    private String companyName;   //公司名称
    private String customerType;  //客户类型：1-预付款客户，2-月结客户，3-票到付款客户
    private List<OrderBrandFee> orderBrandFees;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public String getContractBuyer() {
        return contractBuyer;
    }

    public void setContractBuyer(String contractBuyer) {
        this.contractBuyer = contractBuyer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public List<OrderBrandFee> getOrderBrandFees() {
        return orderBrandFees;
    }

    public void setOrderBrandFees(List<OrderBrandFee> orderBrandFees) {
        this.orderBrandFees = orderBrandFees;
    }
}
