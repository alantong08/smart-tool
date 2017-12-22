package com.citi.alan.myproject.tess4j.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String nickName;
    private Date scanDate;
    private String orderNum;
    private String merchantName;
    private String actualAmount;
    private String transferType;
    private String activityType;
    private String rate;
    private String alipayAccount;
    private String comment;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public Date getScanDate() {
        return scanDate;
    }
    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }
    public String getTransferType() {
        return transferType;
    }
    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }
    public String getActivityType() {
        return activityType;
    }
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getAlipayAccount() {
        return alipayAccount;
    }
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
      
    }
    
    
    
    
}
