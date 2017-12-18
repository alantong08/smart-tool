package com.citi.alan.myproject.tess4j.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.citi.alan.myproject.tess4j.dict.MerchantsDict;
import com.citi.alan.myproject.tess4j.enu.TransferType;
import com.citi.alan.myproject.tess4j.model.BillOrderDetail;
import com.citi.alan.myproject.tess4j.service.api.BillOrderDetectorService;
import com.citi.alan.myproject.tess4j.util.ImageUtil;
import com.citi.alan.myproject.tess4j.util.TessercatUtil;

import net.sourceforge.tess4j.TesseractException;

@Service
public class BillOrderDetectorServiceImpl implements BillOrderDetectorService {

    @Autowired
    private TessercatUtil tessercatUtil;
    
    @Autowired
    private ImageUtil imageUtil;
    
    @Value("${alipay.identifier}")
    private String alipayIdentifier;
    
    @Value("${elian.identifier}")
    private String elianIdentifier;

    @Override
    public BillOrderDetail detetctBillOrderDetail(File file, String activityType) {
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        try {
            String newFile = imageUtil.processImageThreshold(file.getAbsolutePath());
            String result = tessercatUtil.parseImage(new File(newFile));

            if(result.contains(alipayIdentifier)){
               System.out.println("this is alipay");
               billOrderDetail = processAlipayOrder(result, activityType);
            }else if(result.contains(elianIdentifier)){
                System.out.println("this is elian pay");
                billOrderDetail = processElianOrder(result, activityType);
            }else{
                System.out.println("this is weixin pay");
                billOrderDetail = processWeixinOrder(result, activityType);
            }

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        }
        return billOrderDetail;

    }
    
    private BillOrderDetail processAlipayOrder(String result, String activityType){
        String[] list = result.split("\n");
        Map<String, String> resultMap = new HashMap<String, String>();
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        
        for (int i = 0; i < list.length; i++) {
            String singleLineResult = list[i];
            String key = singleLineResult.substring(0, singleLineResult.indexOf(" ")+1);
            String value = singleLineResult.substring(singleLineResult.indexOf(key)+key.length());
            if(key.contains("单金")) {
            		key = "订单金额";
            }else if(key.contains("随机立减")) {
            		key = "随机立减";
            }
            resultMap.put(key.trim(), value.trim());
        }
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + "\t value:" + entry.getValue());
        }
        
        String date = resultMap.get("创建时间");
        String year = date.substring(0, 4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        billOrderDetail.setDate(month+"/"+day+"/"+year);

        String merchantsOrderNum = resultMap.get("").replaceAll("o", "0");
        billOrderDetail.setOrderNum(merchantsOrderNum);
        String merchantsNo = merchantsOrderNum.substring(0, 12);
        String merchantName = MerchantsDict.merchants.get(merchantsNo);
        billOrderDetail.setMerchantName(merchantName);
        
        String orderAmount = resultMap.get("订单金额").replaceAll("o", "0");
        Float actualAmount = Float.valueOf( orderAmount);
        String discountedPrice = resultMap.get("随机立减").substring(1).replaceAll("o", "0");;
        if(!StringUtils.isEmpty(discountedPrice)) {
        		Float disPrice = Float.valueOf(discountedPrice);
        		actualAmount = actualAmount-disPrice;
        }
        billOrderDetail.setActualPrice(actualAmount);   
        billOrderDetail.setTransferType(TransferType.ALIPAY.getValue());
        billOrderDetail.setActivityType(activityType);
        billOrderDetail.setName("童生");
        billOrderDetail.setNickName("兔少");
        billOrderDetail.setRate("0.3");
        billOrderDetail.setAlipayAccount("alan_tong@qq.com");
        return billOrderDetail;
    }
    
    /**
     * processs Elian Pay order
     * @param result
     * @param activityType
     * @return
     */
    private BillOrderDetail processElianOrder(String result, String activityType){

        String[] list = result.split("\n");
        Map<String, String> resultMap = new HashMap<String, String>();
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        
        for (int i = 0; i < list.length; i++) {
            String singleLineResult = list[i];
            String key = singleLineResult.substring(0, singleLineResult.indexOf(" ")+1);
            String value = singleLineResult.substring(singleLineResult.indexOf(key)+key.length());
            if(key.contains("实付金额")) {
                    key = "订单金额";
            }else if(value.contains("时间")) {
                    key = "支付时间";
                    value = value.substring(value.indexOf("时间")+2).trim();
            }else if(key.contains("订单编号")) {
                    key = "商户订单号";
            }
            resultMap.put(key.trim(), value.trim());
        }

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + "\t value:" + entry.getValue());
        }
        String date = resultMap.get("支付时间");
        String year = date.substring(0, 4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        billOrderDetail.setDate(month+"/"+day+"/"+year);
        
        String orderNum = resultMap.get("商户订单号").replaceAll("o", "0");
        billOrderDetail.setOrderNum(orderNum);
        String merchantsNo = orderNum.substring(0, 12);
        String merchantName = MerchantsDict.merchants.get(merchantsNo);
        billOrderDetail.setMerchantName(merchantName);
        
        String orderAmount = resultMap.get("订单金额").replaceAll("o", "0");
        orderAmount = orderAmount.substring(0, orderAmount.indexOf("元")).replace(" ", "");
        Float actualAmount = Float.valueOf( orderAmount);
        billOrderDetail.setActualPrice(actualAmount);
        billOrderDetail.setTransferType(TransferType.ELIANPAY.getValue());
        billOrderDetail.setActivityType(activityType);
        billOrderDetail.setName("童生");
        billOrderDetail.setNickName("兔少");
        billOrderDetail.setRate("0.3");
        billOrderDetail.setAlipayAccount("alan_tong@qq.com");
        return billOrderDetail;
    
    }
    
    private BillOrderDetail processWeixinOrder(String result, String activityType){
        String[] list = result.split("\n");
        Map<String, String> resultMap = new HashMap<String, String>();
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        
        for (int i = 0; i < list.length; i++) {
            String singleLineResult = list[i];
            String key = singleLineResult.substring(0, singleLineResult.indexOf(" ")+1);
            String value = singleLineResult.substring(singleLineResult.indexOf(key)+key.length());
            if(key.contains("付款金额")) {
                    key = "订单金额";
            }else if(value.contains("单号")) {
                key = "商户订单号";
                value = value.substring(value.indexOf("单号")+3).trim();
            }
            resultMap.put(key.trim(), value.trim());
        }
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + "\t value:" + entry.getValue());
        }
        String date = resultMap.get("支付时间");
        String year = date.substring(0, 4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        billOrderDetail.setDate(month+"/"+day+"/"+year);
        
        String orderNum = resultMap.get("商户订单号").replaceAll("o", "0");
        billOrderDetail.setOrderNum(orderNum);
        String merchantsNo = orderNum.substring(0, 12);
        String merchantName = MerchantsDict.merchants.get(merchantsNo);
        billOrderDetail.setMerchantName(merchantName);
        
        
        String orderAmount = resultMap.get("订单金额").substring(1).replaceAll("o", "0").replaceAll(" ", "");
        Float actualAmount = Float.valueOf( orderAmount);
        billOrderDetail.setActualPrice(actualAmount);   
        billOrderDetail.setTransferType(TransferType.WEIXINPAY.getValue());
        billOrderDetail.setActivityType(activityType);
        billOrderDetail.setName("童生");
        billOrderDetail.setNickName("兔少");
        billOrderDetail.setRate("0.3");
        billOrderDetail.setAlipayAccount("alan_tong@qq.com");
        return billOrderDetail;
    }

}
