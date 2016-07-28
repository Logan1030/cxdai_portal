package com.cxdai.portal.account.vo;

import java.io.Serializable;

import com.cxdai.common.page.BaseCnd;

public class FriendCnd extends BaseCnd implements Serializable{
	private static final long serialVersionUID = -67496093806755950L;

	/**
	 * 用户ID
	 */
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
