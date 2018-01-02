package com.citi.alan.myproject.tess4j.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.citi.alan.myproject.tess4j.dao.OrderDetailDao;
import com.citi.alan.myproject.tess4j.dao.UserInfoDao;
import com.citi.alan.myproject.tess4j.dict.Dictionary;
import com.citi.alan.myproject.tess4j.entity.Merchant;
import com.citi.alan.myproject.tess4j.entity.OrderDetail;
import com.citi.alan.myproject.tess4j.entity.UserInfo;
import com.citi.alan.myproject.tess4j.enu.ActivityType;
import com.citi.alan.myproject.tess4j.enu.TransferType;
import com.citi.alan.myproject.tess4j.model.BillOrderDetail;
import com.citi.alan.myproject.tess4j.service.api.BillOrderDetectorService;
import com.citi.alan.myproject.tess4j.service.api.MerchantService;
import com.citi.alan.myproject.tess4j.util.DateUtil;
import com.citi.alan.myproject.tess4j.util.ImageUtil;
import com.citi.alan.myproject.tess4j.util.TessercatUtil;

import net.sourceforge.tess4j.TesseractException;

@Service
public class BillOrderDetectorServiceImpl implements BillOrderDetectorService {

	@Autowired
	private TessercatUtil tessercatUtil;

	@Autowired
	private ImageUtil imageUtil;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private UserInfoDao userInfoDao;

	@Value("${alipay.identifier}")
	private String alipayIdentifier;

	@Value("${elian.identifier}")
	private String elianIdentifier;
	
	private Map<String, Merchant> map = new HashMap<String, Merchant>();
	
	private DecimalFormat decimalFormat=new DecimalFormat(".00");
	
	@PostConstruct
	public void loadMerchantMap() {
		map = merchantService.getMerchantMap();
	}
	
	public List<BillOrderDetail> getBillOrderDetailList(String mobile){
	    List<BillOrderDetail> billOrderDetails = new ArrayList<BillOrderDetail>();
	    try{
	        UserInfo userInfo = userInfoDao.findByMobile(mobile);
	        if(userInfo==null){
	            return billOrderDetails;
	        }
	        List<OrderDetail> orderDetails = orderDetailDao.findByUserInfoOrderByCreatedDateDesc(userInfo);	      
	        if(orderDetails!=null){
	            for (OrderDetail orderDetail : orderDetails) {
	                BillOrderDetail billOrderDetail = new BillOrderDetail();
	                BeanUtils.copyProperties(billOrderDetail, orderDetail);
	                billOrderDetail.setActivityType(Dictionary.activityMap.get(orderDetail.getActivityType()));
	                billOrderDetail.setTransferType(Dictionary.tranferMap.get(orderDetail.getTransferType()));
	                billOrderDetail.setUserName(userInfo.getUserName());
	                billOrderDetail.setNickName(userInfo.getNickName());
	                billOrderDetail.setAlipayAccount(userInfo.getAlipayAccount());
	                billOrderDetails.add(billOrderDetail);
	            }
 
	        }
	    }catch(Exception se){
	        se.printStackTrace();
	    }
	    return billOrderDetails;
	}
	
	 public List<BillOrderDetail> getAllBillOrderDetailList(){
	     List<OrderDetail> orderDetails = orderDetailDao.findAll();
	    
	     List<BillOrderDetail> billOrderDetails = new ArrayList<BillOrderDetail>();
	     try{
	         if(orderDetails!=null){
	             Collections.sort(orderDetails, (o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate()));
	             for (OrderDetail orderDetail : orderDetails) {
	                 BillOrderDetail billOrderDetail = new BillOrderDetail();
	                 BeanUtils.copyProperties(billOrderDetail, orderDetail);
	                 billOrderDetail.setActivityType(Dictionary.activityMap.get(orderDetail.getActivityType()));
	                 billOrderDetail.setTransferType(Dictionary.tranferMap.get(orderDetail.getTransferType()));
	                 billOrderDetail.setUserName(orderDetail.getUserInfo().getUserName());
	                 billOrderDetail.setNickName(orderDetail.getUserInfo().getNickName());
	                 billOrderDetail.setAlipayAccount(orderDetail.getUserInfo().getAlipayAccount());
	                 billOrderDetails.add(billOrderDetail);
	             }

	         }
	     }catch(Exception se){
	         se.printStackTrace();
	     }
	     return billOrderDetails;

	 }
	
	public boolean saveOrderDetail(BillOrderDetail billOrderDetail){
	    OrderDetail orderDetail = new OrderDetail();
	    boolean flag = false;
	    try {
            BeanUtils.copyProperties(orderDetail, billOrderDetail);
            Merchant merchant = map.get(billOrderDetail.getMerchantName());
            orderDetail.setMerchantName(merchant.getMerchantName());
            UserInfo userInfo = userInfoDao.findByMobile(billOrderDetail.getMobile());
            orderDetail.setUserInfo(userInfo);
            orderDetail.setCreatedDate(DateUtil.getFormatDateStr("yyyy/MM/dd HH:mm:ss"));
            orderDetailDao.save(orderDetail);
            flag = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
	    return flag;
	}

	@Override
	public BillOrderDetail detetctBillOrderDetail(File file, String activityType, UserInfo user) {
		BillOrderDetail billOrderDetail = new BillOrderDetail();
		try {
			String newFile = imageUtil.processImageThreshold(file.getAbsolutePath());
			String result = tessercatUtil.parseImage(new File(newFile));

			if (result.contains(alipayIdentifier)) {
				System.out.println("this is alipay");
				billOrderDetail = processAlipayOrder(result, activityType);
				// chceckMerchantIsAllowed(); 
			} else if (result.contains(elianIdentifier)) {
				System.out.println("this is elian pay");
				billOrderDetail = processElianOrder(result, activityType);
			} else {
				System.out.println("this is weixin pay");
				billOrderDetail = processWeixinOrder(result, activityType);
			}

		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}

		setUserInfoDetail(billOrderDetail, user, activityType);
		return billOrderDetail;

	}

	private void setUserInfoDetail(BillOrderDetail billOrderDetail, UserInfo userInfo, String activityType) {
		String nickName = userInfo.getNickName();
		String transferType = billOrderDetail.getTransferType();
		billOrderDetail.setUserName(userInfo.getUserName());
		billOrderDetail.setNickName(userInfo.getNickName());
		billOrderDetail.setAlipayAccount(userInfo.getAlipayAccount());
		String rate = "";
		if (ActivityType.GENERAL_ASSISTS.getValue().equals(activityType)) {// general assists
			if (TransferType.ELIANPAY.getValue() == transferType || TransferType.WEIXINPAY.getValue() == transferType) {
				if (nickName.contains("一级"))
					rate = "0.25";
				else if (nickName.contains("二级"))
					rate = "0.3";
				else if (nickName.contains("三级"))
					rate = "0.35";
				else
					rate = "1.0";
			} else if (TransferType.ALIPAY.getValue() == transferType) {
				if (nickName.contains("一级") || nickName.contains("二级") || nickName.contains("三级")) {
					rate = "0.35";
				} else
					rate = "1.0";
			}

		} else if (ActivityType.NO_ASSISTS.getValue().equals(activityType)) {// don't join assists
			rate = "0.4";
		} else if (ActivityType.LUCKY_GUY.getValue().equals(activityType) || ActivityType.JIAOTONG.getValue().equals(activityType)) {// lucky guy or JiaoTong
			rate = "0.1";
		} else if (ActivityType.SUPER_ASSISTS.getValue().equals(activityType)) {// super assists
			if (TransferType.ALIPAY.getValue() == transferType) {
				rate = "0.3";
			} else if (TransferType.WEIXINPAY.getValue() == transferType) {
				rate = "0.25";
			} else if (TransferType.ELIANPAY.getValue() == transferType) {
				rate = "0.2";
			}
		}
		billOrderDetail.setRate(rate);

	}

	private BillOrderDetail processAlipayOrder(String result, String activityType) {
		String[] list = result.split("\n");
		Map<String, String> resultMap = new HashMap<String, String>();
		BillOrderDetail billOrderDetail = new BillOrderDetail();

		for (int i = 0; i < list.length; i++) {
			String singleLineResult = list[i];
			String key = singleLineResult.substring(0, singleLineResult.indexOf(" ") + 1);
			String value = singleLineResult.substring(singleLineResult.indexOf(key) + key.length());
			if (key.contains("单金")) {
				key = "订单金额";
			} else if (key.contains("随机立减")) {
				key = "随机立减";
			}
			resultMap.put(key.trim(), value.trim());
		}
		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			System.out.println("key:" + entry.getKey() + "\t value:" + entry.getValue());
		}

		try {
			String date = resultMap.get("创建时间");
			setScanDate(billOrderDetail, date);
		} catch (Exception se) {
			se.printStackTrace();
		}

		try {
			String merchantsOrderNum = resultMap.get("").replaceAll("o", "0");
			billOrderDetail.setOrderNum(merchantsOrderNum);
			String merchantsNo = merchantsOrderNum.substring(0, 12);
			
			//Merchant merchant = map.get(merchantsNo);
			billOrderDetail.setMerchantName(merchantsNo);
		}catch(Exception se) {
			se.printStackTrace();
		}

		try {
			String orderAmount = resultMap.get("订单金额").replaceAll("o", "0");
			Float actualAmount = Float.valueOf(orderAmount);
			String discountedPrice = resultMap.get("随机立减").substring(1).replaceAll("o", "0").replaceAll("O", "0");

			if (!StringUtils.isEmpty(discountedPrice)) {
				Float disPrice = Float.valueOf(discountedPrice);
				actualAmount = actualAmount - disPrice;
				billOrderDetail.setDiscountedPrice(decimalFormat.format(disPrice));
			}
			billOrderDetail.setActualPrice(decimalFormat.format(actualAmount));
		} catch (Exception se) {
			se.printStackTrace();
		}

		billOrderDetail.setTransferType(TransferType.ALIPAY.getValue());
		billOrderDetail.setActivityType(activityType);

		return billOrderDetail;
	}

    private void setScanDate(BillOrderDetail billOrderDetail, String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
       // billOrderDetail.setDate(month + "/" + day + "/" + year);
        billOrderDetail.setScanDate(year+"-"+month+"-"+day);
    }

	/**
	 * processs Elian Pay order
	 * 
	 * @param result
	 * @param activityType
	 * @return
	 */
	private BillOrderDetail processElianOrder(String result, String activityType) {

		String[] list = result.split("\n");
		Map<String, String> resultMap = new HashMap<String, String>();
		BillOrderDetail billOrderDetail = new BillOrderDetail();

		for (int i = 0; i < list.length; i++) {
			String singleLineResult = list[i];
			String key = singleLineResult.substring(0, singleLineResult.indexOf(" ") + 1);
			String value = singleLineResult.substring(singleLineResult.indexOf(key) + key.length());
			if (key.contains("实付金额")) {
				key = "订单金额";
			} else if (value.contains("时间") || key.contains("交易时间")) {
				key = "支付时间";
				if (value.indexOf("时间") > 0) {
					value = value.substring(value.indexOf("时间") + 2).trim();
				} else {
					value = value.trim();
				}

			} else if (key.contains("订单编号")) {
				key = "商户订单号";
			}
			resultMap.put(key.trim(), value.trim());
		}

		for (Map.Entry<String, String> entry : resultMap.entrySet()) {
			System.out.println("key:" + entry.getKey() + "\t value:" + entry.getValue());
		}

		try {
			String date = resultMap.get("支付时间");
			setScanDate(billOrderDetail, date);
		} catch (Exception se) {
			se.printStackTrace();
		}

		try {
			String orderNum = resultMap.get("商户订单号").replaceAll("o", "0").replaceAll("O", "0");
			billOrderDetail.setOrderNum(orderNum);
			String merchantsNo = orderNum.substring(0, 12);	
			billOrderDetail.setMerchantName(merchantsNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String orderAmount = resultMap.get("订单金额").replaceAll("o", "0").replaceAll(",", "").replaceAll("，", "");
			orderAmount = orderAmount.substring(0, orderAmount.indexOf(".")).replace(" ", "");
			float orderPrice = Float.valueOf(orderAmount);
			billOrderDetail.setActualPrice(decimalFormat.format(orderPrice));
		} catch (Exception se) {
			se.printStackTrace();
		}

		billOrderDetail.setTransferType(TransferType.ELIANPAY.getValue());
		billOrderDetail.setActivityType(activityType);

		return billOrderDetail;

	}

	private BillOrderDetail processWeixinOrder(String result, String activityType) {
		String[] list = result.split("\n");
		Map<String, String> resultMap = new HashMap<String, String>();
		BillOrderDetail billOrderDetail = new BillOrderDetail();

		for (int i = 0; i < list.length; i++) {
			String singleLineResult = list[i];
			String key = singleLineResult.substring(0, singleLineResult.indexOf(" ") + 1);
			String value = singleLineResult.substring(singleLineResult.indexOf(key) + key.length());
			if (key.contains("付款金额")) {
				key = "订单金额";
			} else if (value.contains("单号")) {
				key = "商户订单号";
				value = value.substring(value.indexOf("单号") + 3).trim();
			}
			resultMap.put(key.trim(), value.trim());
		}

		try {
			String date = resultMap.get("支付时间");
			setScanDate(billOrderDetail, date);
		} catch (Exception se) {
			se.printStackTrace();
		}

		try {
			String orderNum = resultMap.get("商户订单号").replaceAll("o", "0").replaceAll("O", "0");
			billOrderDetail.setOrderNum(orderNum);
			String merchantsNo = orderNum.substring(0, 12);
			billOrderDetail.setMerchantName(merchantsNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String orderAmount = resultMap.get("订单金额").substring(1).replaceAll("o", "0").replaceAll(" ", "");
			float orderPrice = Float.valueOf(orderAmount);
			billOrderDetail.setActualPrice(decimalFormat.format(orderPrice));
		} catch (Exception e) {
			e.printStackTrace();
		}

		billOrderDetail.setTransferType(TransferType.WEIXINPAY.getValue());
		billOrderDetail.setActivityType(activityType);
		return billOrderDetail;
	}

}
