package com.citi.alan.myproject.tess4j.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlgraphics.util.io.FlateEncodeOutputStream;
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
            }else if(key.contains("创建时间")) {
        			key = "创建时间";
            }else if(key.contains("")) {
        			key = "商户订单号";
            }
            resultMap.put(key.trim(), value.trim());
        }
        
//      for(Map.Entry<String, String> entry : resultMap.entrySet()){
//          System.out.println("key:"+entry.getKey()+"\t value:"+entry.getValue());
//      }
        
        String dateStr = resultMap.get("创建时间");
        String date = dateStr.split(" ")[0];
        String [] dateArray = date.split("-");
        String dateValue = dateArray[1]+"/"+dateArray[2]+"/"+dateArray[1];
        billOrderDetail.setDate(dateValue);
        String merchantsOrderNum = resultMap.get("商户订单号");
        billOrderDetail.setOrderNum(merchantsOrderNum);
        String merchantsNo = merchantsOrderNum.substring(0, 12);
        String merchantName = MerchantsDict.merchants.get(merchantsNo);
        billOrderDetail.setMerchantName(merchantName);
        String orderAmount = resultMap.get("订单金额");
        Float actualAmount = Float.valueOf( orderAmount);
        String discountedPrice = resultMap.get("随机立减");
        if(!StringUtils.isEmpty(discountedPrice)) {
        		Float disPrice = Float.valueOf(discountedPrice);
        		actualAmount = actualAmount+disPrice;
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
    
//    private BillOrderDetail processWeixinOrder(String result){
//        
//    }
//    
//    private BillOrderDetail processElianOrder(String result){
//        
//    }

}
