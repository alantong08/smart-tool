package com.citi.alan.myproject.tess4j.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;

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
    public BillOrderDetail detetctBillOrderDetail(File file, String rate) {
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        try {
            String newFile = imageUtil.processImageThreshold(file.getAbsolutePath());
            String result = tessercatUtil.parseImage(new File(newFile));

            if(result.contains(alipayIdentifier)){
               System.out.println("this is alipay");
               billOrderDetail = processAlipayOrder(result);
            }

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        }
        return billOrderDetail;

    }
    
    private BillOrderDetail processAlipayOrder(String result){
        String[] list = result.split("\n");
        Map<String, String> resultMap = new HashMap<String, String>();
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        
        for (int i = 0; i < list.length; i++) {
            String singleLineResult = list[i];
            String key = singleLineResult.substring(0, singleLineResult.indexOf(" ")+1);
            String value = singleLineResult.substring(singleLineResult.indexOf(key)+key.length());
            resultMap.put(key, value.trim());
        }
        billOrderDetail.setMerchantsName(resultMap.get("商品说明"));
        String orderNum = resultMap.get("订单号");
        if(StringUtils.isEmpty(orderNum)){
            orderNum = resultMap.get("i丁单号");
        }
        billOrderDetail.setOrderNum(orderNum);
        //billOrderDetail.setDate(date);
        billOrderDetail.setRate("0.3");
        billOrderDetail.setActiveType("高端群特供噜卡");
        billOrderDetail.setName("Mr Tong");
        billOrderDetail.setNickName("兔少");
        
//        for(Map.Entry<String, String> entry : resultMap.entrySet()){
//            System.out.println("key:"+entry.getKey()+"\t value:"+entry.getValue());
//        }

       
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
