package com.citi.alan.myproject.tess4j.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.citi.alan.myproject.tess4j.util.ImageUtil;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;

@RestController
@RequestMapping("/tess4j")
public class TesseractController {

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
	
	@RequestMapping(value = "/test2")
	@ResponseBody
	String test2() throws IOException, TesseractException {
		return parseImage2();
	}

	private String parseImage1() throws IOException, TesseractException {
		Tesseract1 instance = new Tesseract1();
		instance.setLanguage("chi_sim+eng");
		instance.setDatapath("/Users/gongjiao/git/smart-tool/src/main/resources/tessdata");
		System.out.println("doOCR on a PNG image");
		System.out.println("--------------------alipay bill-------------");
		File imageFile = new ClassPathResource("test-data/ok.jpg").getFile();
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
	
	private String parseImage2() {
		Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping  
		String result = null;
		try {
			//File newfile = ClearImageHelper.doClean(imageFile);
			instance.setDatapath("/Users/gongjiao/git/smart-tool/src/main/resources/tessdata");
			instance.setLanguage("chi_sim");
			// 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
			//BufferedImage textImage = ImageHelper.convertImageToGrayscale(ImageHelper.getSubImage(panel.image, startX, startY, endX, endY));
			
			File imageFile = new ClassPathResource("test-data/weixin1.png").getFile();
			BufferedImage textImage = ImageHelper.convertImageToGrayscale(ImageIO.read(imageFile));
			// 图片锐化,自己使用中影响识别率的主要因素是针式打印机字迹不连贯,所以锐化反而降低识别率
//		     textImage = ImageHelper.convertImageToBinary(textImage);
		    // 图片放大5倍,增强识别率(很多图片本身无法识别,放大5倍时就可以轻易识,但是考滤到客户电脑配置低,针式打印机打印不连贯的问题,这里就放大5倍)
			//textImage = ImageHelper.getScaledInstance(textImage, textImage.getWidth() * 5, textImage.getHeight() * 5);
			result = instance.doOCR(textImage);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}