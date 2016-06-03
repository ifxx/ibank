package com.sample.iBank.rest;

public class Restaccount {
	
	private String userid = "";
	private String accountno= ""; 
	
	public Restaccount() {
		super();
	}

	public Restaccount(String id, String accountno) {
		super();
		this.userid = id;
		this.accountno = accountno;		
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


	
	

}
