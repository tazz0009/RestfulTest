package com.tistory.tazz009.rest.service;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class GridVO {
	private String page;
	private String total;
	private String records;
	private List<Object> rows;
	private UserDataVO userData;
	
	public GridVO() {
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public UserDataVO getUserData() {
		return userData;
	}

	public void setUserData(UserDataVO userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, 
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
