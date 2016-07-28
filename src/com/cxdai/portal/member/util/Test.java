package com.cxdai.portal.member.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cxdai.portal.member.util.BaiDuIp.Data;
import com.cxdai.portal.member.util.BaiDuIp.IpAddr;
import com.cxdai.portal.statics.BusinessConstants;
import com.cxdai.portal.util.JsonUtils;

public class Test {
	
	public static void main(String[] args) {
		String httpUrl = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
		String httpArg = "ip=116.236.196.146";
		String jsonResult = request(httpUrl, httpArg);
		Map<String, Object>map=(Map<String, Object>) JsonUtils.json2Map(jsonResult);
		if(BusinessConstants.SUCCESS.equals(map.get("errMsg") )){
			System.out.println(map.get("retData"));
		}
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	     }
		 
	}
	static class Baidu {
		private String errNum;
		private String errMsg;
		private List<Data> retData = new ArrayList<>(0);
	
		public String getErrNum() {
			return errNum;
		}


		public void setErrNum(String errNum) {
			this.errNum = errNum;
		}


		public String getErrMsg() {
			return errMsg;
		}


		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}


		public List<Data> getRetData() {
			return retData;
		}


		public void setRetData(List<Data> retData) {
			this.retData = retData;
		} 
	}
	
	static class Data {
		private String location;
		private String titlecont;
		private String origip;
		private String origipquery;
		private String showlamp;
		private Integer showLikeShare;
		private Integer shareImage;
		@JsonProperty("ExtendedLocation")
		private String extendedLocation;
		@JsonProperty("OriginQuery")
		private String originQuery;
		private String tplt;
		private String resourceid;
		private String fetchkey;
		private String appinfo;
		@JsonProperty("role_id")
		private Integer roleId;
		@JsonProperty("disp_type")
		private Integer dispType;
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getTitlecont() {
			return titlecont;
		}
		public void setTitlecont(String titlecont) {
			this.titlecont = titlecont;
		}
		public String getOrigip() {
			return origip;
		}
		public void setOrigip(String origip) {
			this.origip = origip;
		}
		public String getOrigipquery() {
			return origipquery;
		}
		public void setOrigipquery(String origipquery) {
			this.origipquery = origipquery;
		}
		public String getShowlamp() {
			return showlamp;
		}
		public void setShowlamp(String showlamp) {
			this.showlamp = showlamp;
		}
		public Integer getShowLikeShare() {
			return showLikeShare;
		}
		public void setShowLikeShare(Integer showLikeShare) {
			this.showLikeShare = showLikeShare;
		}
		public Integer getShareImage() {
			return shareImage;
		}
		public void setShareImage(Integer shareImage) {
			this.shareImage = shareImage;
		}
		public String getExtendedLocation() {
			return extendedLocation;
		}
		public void setExtendedLocation(String extendedLocation) {
			this.extendedLocation = extendedLocation;
		}
		public String getOriginQuery() {
			return originQuery;
		}
		public void setOriginQuery(String originQuery) {
			this.originQuery = originQuery;
		}
		public String getTplt() {
			return tplt;
		}
		public void setTplt(String tplt) {
			this.tplt = tplt;
		}
		public String getResourceid() {
			return resourceid;
		}
		public void setResourceid(String resourceid) {
			this.resourceid = resourceid;
		}
		public String getFetchkey() {
			return fetchkey;
		}
		public void setFetchkey(String fetchkey) {
			this.fetchkey = fetchkey;
		}
		public String getAppinfo() {
			return appinfo;
		}
		public void setAppinfo(String appinfo) {
			this.appinfo = appinfo;
		}
		public Integer getRoleId() {
			return roleId;
		}
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
		public Integer getDispType() {
			return dispType;
		}
		public void setDispType(Integer dispType) {
			this.dispType = dispType;
		}
	}
	public static class IpAddr {
		private static final IpAddr DEFAULT = new IpAddr();
		
		private String area;
		private String province;
		private String city;
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	}
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "4a57f0d72b8d7c9a3673f72e43d8b856");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
