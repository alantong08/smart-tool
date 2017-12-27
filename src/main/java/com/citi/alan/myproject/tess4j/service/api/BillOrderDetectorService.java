package com.citi.alan.myproject.tess4j.service.api;

import java.io.File;

import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.model.BillOrderDetail;

public interface BillOrderDetectorService {

    public BillOrderDetail detetctBillOrderDetail(File file, String rate, UserInfo user);
    
    public boolean saveOrderDetail(BillOrderDetail billOrderDetail);
}
