package com.fuxiaosong.openregion.model;

import java.util.ArrayList;

public class Response {

	private String code;
	private ArrayList<Region> data;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList<Region> getData() {
		return data;
	}
	public void setData(ArrayList<Region> data) {
		this.data = data;
	}
	
}
