package com.cxdai.portal.quickFinancing.service;

import javax.servlet.http.HttpServletRequest;

import com.cxdai.portal.quickFinancing.entity.QuickFinancing;

public interface QuickFinancingService {

   public String insertQucikFinance(QuickFinancing quickFinancing,HttpServletRequest request)throws Exception; 
}
