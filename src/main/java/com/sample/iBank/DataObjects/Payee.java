package com.sample.iBank.DataObjects;

import java.io.Serializable;

public class Payee implements Serializable {
	private String name;
	private String payeeAccNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayeeAccNo() {
		return payeeAccNo;
	}
	public void setPayeeAccNo(String payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}
	
	
}
