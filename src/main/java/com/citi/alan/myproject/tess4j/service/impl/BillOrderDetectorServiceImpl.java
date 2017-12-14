package com.citi.alan.myproject.tess4j.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Override
    public void detetctBillOrderDetail(File file) {
       
        try {
            
          String newFile = imageUtil.processImageThreshold(file.getAbsolutePath());
          tessercatUtil.parseImage(new File(newFile));
            
          // BillOrderDetail billOrderDetail = tessercatUtil.parseImage(file);
//            if(billOrderDetail==null){
//                String newFile = imageUtil.processImageThreshold(file.getAbsolutePath());
//                tessercatUtil.parseImage(new File(newFile));
//            }

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        }

    }

}
