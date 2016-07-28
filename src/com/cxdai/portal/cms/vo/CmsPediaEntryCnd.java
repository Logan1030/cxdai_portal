package com.cxdai.portal.cms.vo;

import java.io.Serializable;

import com.cxdai.common.page.BaseCnd;

public class CmsPediaEntryCnd extends BaseCnd implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}