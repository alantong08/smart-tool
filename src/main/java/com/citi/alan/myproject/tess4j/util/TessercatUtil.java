package com.citi.alan.myproject.tess4j.util;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.citi.alan.myproject.tess4j.model.BillOrderDetail;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

@Component
public class TessercatUtil {

    @Value("${tessdata.lib.path}")
    private String datapath;
    
    public BillOrderDetail parseImage(File imageFile) throws IOException, TesseractException {
        Tesseract1 instance = new Tesseract1();
        instance.setLanguage("chi_sim+eng");
        instance.setDatapath(datapath);
        System.out.println("doOCR on a PNG image");
        System.out.println("--------------------bill detail-------------");
        String result = "error";
        String response = null;
        result = instance.doOCR(imageFile);
        String[] list = result.split("\n");
        BillOrderDetail billOrderDetail = new BillOrderDetail();
        for (int i = 0; i < list.length; i++) {
            if(list[i].contains(""))
            response = response + list[i] + "<br>";
        }
        System.out.println(result);
        return billOrderDetail;
    }
}
