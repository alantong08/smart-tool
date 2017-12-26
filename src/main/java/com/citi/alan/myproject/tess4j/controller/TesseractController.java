package com.citi.alan.myproject.tess4j.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.citi.alan.myproject.tess4j.entity.Merchant;
import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.model.BillOrderDetail;
import com.citi.alan.myproject.tess4j.service.api.BillOrderDetectorService;
import com.citi.alan.myproject.tess4j.service.api.MerchantService;
import com.citi.alan.myproject.tess4j.service.api.UserInfoService;
import com.citi.alan.myproject.tess4j.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/user/tess4j")
public class TesseractController {

	@Value("${upload.file.path}")
	private String uploadFilePath;

	private final static String DATE_PATTERN = "yyyyMMddHHmmssSSS";

	@Autowired
	private BillOrderDetectorService billOrderDetectorService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private MerchantService merchantService;

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView handleUploadFile(HttpServletRequest request) throws JsonProcessingException {
		BillOrderDetail detail = null;
		try {
			String activityType = request.getParameter("activityType");
			MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file-order");
			String mobile = (String) request.getSession().getAttribute("mobile");
			UserInfo user = userInfoService.getUserByMobile(mobile);
			String originalFileName = file.getOriginalFilename();
			if (file != null && originalFileName != null && originalFileName.length() > 0) {
				String newFileName = DateUtil.getFormatDateStr(DATE_PATTERN)
						+ originalFileName.substring(originalFileName.lastIndexOf("."));
				File newFile = new File(uploadFilePath + newFileName);
				file.transferTo(newFile); 
				detail = billOrderDetectorService.detetctBillOrderDetail(newFile, activityType, user);
			}
		} catch (Exception se) {
			se.printStackTrace();
		}

		ModelAndView mView = new ModelAndView("/user/confirm");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(detail);
		mView.addObject("billDetail", json);
		return mView;
	}

	@RequestMapping(value = "/merchant")
	@ResponseBody
	List<Merchant> getMerchantList() throws IOException, TesseractException {
		Map<String, Merchant>  map = merchantService.getMerchantMap();
		List<Merchant> merchants = new ArrayList<>();
		for (Map.Entry<String, Merchant> entry : map.entrySet()) {
			merchants.add(entry.getValue());
		}
		return merchants;
	}
	
	@RequestMapping(value = "/saveBillOrder", method = RequestMethod.POST)
	@ResponseBody
	ModelAndView saveForm(BillOrderDetail billOrderDetail, HttpServletRequest request) throws IOException, TesseractException {
		ModelAndView mView = new ModelAndView("submit");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(billOrderDetail);
		mView.addObject("billDetail", json);
		return mView;
	}

}