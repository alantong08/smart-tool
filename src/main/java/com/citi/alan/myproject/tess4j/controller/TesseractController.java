package com.citi.alan.myproject.tess4j.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/tess4j")
public class TesseractController {
    
    private static String windowsPath = "C://Users//bt14118.NAM//git//smart-tool//src//main//resources//tessdata";
    private static String OSXpath = "/Users/gongjiao/git/smart-tool/src/main/resources/tessdata";

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		String name = request.getParameter("name");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}

		}
		return "upload successful";

	}

	@RequestMapping(value = "/test")
	@ResponseBody
	String test() throws IOException, TesseractException {
		return parseImage1();
	}
	
	private String parseImage1() throws IOException, TesseractException {
		Tesseract1 instance = new Tesseract1();
		instance.setLanguage("chi_sim+eng");

		instance.setDatapath(windowsPath);
		System.out.println("doOCR on a PNG image");
		System.out.println("--------------------alipay bill-------------");
		File imageFile = new ClassPathResource("test-data/elian.png").getFile();
		String result = "error";
		String response = null;
		String billNo = null;
		result = instance.doOCR(imageFile);
		String[] list = result.split("\n");
		for (int i = 0; i < list.length; i++) {
			response = response + list[i] + "<br>";
		}
		// billNo = "支付宝订单号:" + ((list[12] != null) ? list[12] : "NA");
		System.out.println(result);
		return response;
	}
	

}