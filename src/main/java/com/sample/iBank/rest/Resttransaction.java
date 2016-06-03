package com.sample.iBank.rest;

public class Resttransaction {
	
	private String userid;
	private String accountno;
	private String amt;
	private String txnType;
	
	public Resttransaction()
	{
		super();
	}
	
	public Resttransaction(String uid, String acNo, String amt, String txn)
	{
		super();
		this.userid = uid;
		this.accountno = acNo;
		this.amt =  amt;
		this.txnType = txn;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	
	

}
