package com.citi.alan.myproject.tess4j.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BillOrderDetail implements Serializable{

    private static final long serialVersionUID = 1L;
    private String scanDate;
    private String name;
    private String nickName;
    private String orderNum;
    private String merchantName;
    private String activityType;
    private String rate; 
    private Float actualPrice;
    private Float discountedPrice;
    private Float totalPrice;
    private String transferType;
    private String comments;
    private String alipayAccount;
    private String mobile;
    

    public String getScanDate() {
        return scanDate;
    }
    public void setScanDate(String scanDate) {
        this.scanDate = scanDate;
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

    public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }


    public Float getActualPrice() {
        return actualPrice;
    }
    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }
    public Float getDiscountedPrice() {
        return discountedPrice;
    }
    public void setDiscountedPrice(Float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
      
    }
}
