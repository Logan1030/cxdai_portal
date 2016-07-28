package com.cxdai.portal.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	private static final Logger logger = Logger.getLogger(JsonUtils.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> T json2Bean(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			// 忽略在json中有在bean中没有的属性
			objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}
	
	public static String bean2Json(Object bean) {
		if (bean == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(bean);
		} catch (Exception e) {
			logger.error("unconverted bean[" + bean + "]", e);
		}
		return null;
	}

	public static Map<?, ?> json2Map(String json) {
		if (json == null) {
			return null;
		}
		try {
			return objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}
	
	public static List<?> json2List(String json) {
		if (json == null) {
			return null;
		}
		try {
			return objectMapper.readValue(json, List.class);
		} catch (Exception e) {
			logger.error("unconverted json[" + json + "]", e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String json = "{\"responseStatus\":{\"statusCode\":201,\"reasonPhrase\":\"请求成功完成\",\"family\":\"SUCCESSFUL\"},\"timestamp\":\"20140123091029\"}";
		Map result = JsonUtils.json2Map(json);
		int statusCode = Integer.parseInt(((Map) result.get("responseStatus")).get("statusCode").toString());
		System.out.println(statusCode);		
	}
	
	/**
	 * 返回给投之家的json字符串
	 * @author WangQianJin
	 * @title @param jsonStr
	 * @title @param timestamp
	 * @title @return
	 * @date 2015年11月19日
	 */
	public static String returnTzjForJson(String jsonStr,String timestamp) {
		return jsonStr+"&timestamp="+timestamp;
	}
	
	/**
	 * 根据json字符串获取sql字符串
	 * @author WangQianJin
	 * @title @param jsonStr
	 * @title @return
	 * @date 2015年11月30日
	 */
	public static String getSqlStrByJson(String jsonStr) {
		String resultStr="";		
		List<String> result = (List<String>)JsonUtils.json2List(jsonStr);
		if(result!=null && result.size()>0){
			for(String str:result){
				resultStr=resultStr+"'"+str+"',";				
			}
		}
		if(resultStr.length()>0){
			resultStr=resultStr.substring(0,resultStr.length()-1);
		}
		return resultStr;
	}
}
