package com.sample.iBank.DataObjects;

import java.io.Serializable;

public class Transaction implements Serializable {
	
	private String txDate;
	private String details;
	private String txAmount;
	private String balance;
	private String txType;

	
	public String getTxType() {
		return txType;
	}
	public void setTxType(String txType) {
		this.txType = txType;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getTxAmount() {
		return txAmount;
	}
	public void setTxAmount(String txAmount) {
		this.txAmount = txAmount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
