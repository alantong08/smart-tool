package com.citi.alan.myproject.tess4j.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BillOrderDetail {

    private String date;
    private String name;
    private String nickName;
    private String orderNum;
    private String merchantsName;
    private String rate; 
    private String activeType;
    private String comments;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public String getMerchantsName() {
        return merchantsName;
    }
    public void setMerchantsName(String merchantsName) {
        this.merchantsName = merchantsName;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getActiveType() {
        return activeType;
    }
    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
      
    }
    
    
    
}
