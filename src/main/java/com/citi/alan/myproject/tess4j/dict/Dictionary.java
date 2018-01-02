package com.citi.alan.myproject.tess4j.dict;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	public static  Map<String, String> activityMap =new HashMap<String, String>();
	public static  Map<String, String> tranferMap =new HashMap<String, String>(); 
	static {
		activityMap.put("1","记入月底助攻金额");
		activityMap.put("2","选择高费率自用");
		activityMap.put("3","幸运星待遇");
		activityMap.put("4","高端群特供撸卡");
		activityMap.put("5","小娇周周特别活动0.1");
		
		tranferMap.put("1", "阿联");
		tranferMap.put("2", "微信");
		tranferMap.put("3", "支付宝");
	}
}
