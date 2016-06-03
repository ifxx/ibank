package com.sample.iBank.DataObjects;

import java.io.Serializable;

public class Account implements Serializable{
	private String accNo;
	private String balance;
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	
}
