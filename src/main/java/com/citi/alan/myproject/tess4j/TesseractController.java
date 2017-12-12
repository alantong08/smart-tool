package com.citi.alan.myproject.tess4j;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/tess4j")
public class TesseractController {

    @RequestMapping(value = "/test")
    @ResponseBody
    String test() throws IOException {
        
        Tesseract1 instance = new Tesseract1();
        instance.setLanguage("eng");
        instance.setDatapath("C://Users//bt14118.NAM//git//smart-tool//src//main//resources//tessdata");
        System.out.println("doOCR on a PNG image");
        System.out.println("--------------------alipay bill-------------");
        File imageFile = new ClassPathResource("test-data/alipay.png").getFile();
        String result = "error ";
        String response = null;
        String billNo = null;
        try {
            result = instance.doOCR(imageFile);
            String [] list= result.split("\n");
            for (int i = 0; i < list.length; i++) {
                response = response + list[i]+"<br>";
            }
            billNo = "支付宝订单号:"+((list[12]!=null)?list[12]:"NA");

        System.out.println(billNo);

        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return billNo;
    }

}