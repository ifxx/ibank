package com.sample.iBank.Action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

public class TransferFundsAction extends ActionSupport implements ServletRequestAware{
	
	private String fromAccNumber;
	private String toAccNumber;
	private String txfrAmount;
	private String BillerName;
	private String isBillPay = "No";
	
	private HttpServletRequest request;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.request = servletRequest;
 
    }
	
	
	public String getBillerName() {
		return BillerName;
	}
	public void setBillerName(String billerName) {
		BillerName = billerName;
	}
	public String getIsBillPay() {
		return isBillPay;
	}
	public void setIsBillPay(String isBillPay) {
		this.isBillPay = isBillPay;
	}
	public String getFromAccNumber() {
		return fromAccNumber;
	}
	public void setFromAccNumber(String fromAccNumber) {
		this.fromAccNumber = fromAccNumber;
	}
	public String getToAccNumber() {
		return toAccNumber;
	}
	public void setToAccNumber(String toAccNumber) {
		this.toAccNumber = toAccNumber;
	}
	public String getTxfrAmount() {
		return txfrAmount;
	}
	public void setTxfrAmount(String txfrAmount) {
		this.txfrAmount = txfrAmount;
	}
	
	public String execute() {
		
		String msg = "";
		try
		{
			if (isBillPay.equals("Yes"))
			{
				msg = DataAccess.payBill(fromAccNumber, BillerName, txfrAmount, getText("Datafilepath"));
			}
			else
			{
				msg = DataAccess.updateFunds(fromAccNumber, toAccNumber, txfrAmount, getText("Datafilepath"));
			}
			
			addActionMessage(msg);
			return "success";
		}
		catch(Exception ex)
		{
			addActionMessage("Error in transaction. Please try again.");
			return "error";
		}
		
		
	}
	
	public String restTransaction(String accNo, String amt, String txntype)
	{
		String msg = "";
		msg = DataAccess.payiBankInvestor(accNo, amt, getText("Datafilepath"), txntype);
		return msg;
	}
	

}
