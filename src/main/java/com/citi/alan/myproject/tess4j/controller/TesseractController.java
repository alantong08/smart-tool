package com.citi.alan.myproject.tess4j.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.citi.alan.myproject.tess4j.service.api.BillOrderDetectorService;

import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/tess4j")
public class TesseractController {
    
    @Value("${upload.file.path}")
    private String uploadFilePath;
    
    @Autowired
    private BillOrderDetectorService billOrderDetectorService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView handleFileUpload(HttpServletRequest request, ModelAndView mv) {
        try {
            String rate = request.getParameter("box-rate");
            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file-order");
            String originalFileName = file.getOriginalFilename();
            if (file != null && originalFileName != null && originalFileName.length() > 0) {
                String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
                File newFile = new File(uploadFilePath + newFileName);
                file.transferTo(newFile);
                billOrderDetectorService.detetctBillOrderDetail(newFile);
                
            }
        } catch (Exception se) {
            se.printStackTrace();
        }

        ModelAndView mView = new ModelAndView("submit");
        return mView;

    }

    @RequestMapping(value = "/test")
    @ResponseBody
    String test() throws IOException, TesseractException {
        // return tessercatUtil.parseImage();
        return null;
    }

}