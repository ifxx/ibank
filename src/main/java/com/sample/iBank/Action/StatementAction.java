package com.sample.iBank.Action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

public class StatementAction extends ActionSupport implements ServletRequestAware {
	
	private String accNumber;
	private ArrayList<Transaction> txnDetails;
	 private HttpServletRequest request;
	    @Override
	    public void setServletRequest(HttpServletRequest servletRequest) {
	        this.request = servletRequest;
	 
	    }
	
	public ArrayList<Transaction> getTxnDetails() {
		return txnDetails;
	}

	public void setTxnDetails(ArrayList<Transaction> txnDetails) {
		this.txnDetails = txnDetails;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String execute() {
		
		if (accNumber.equalsIgnoreCase("dropdown"))
		{
			addActionError(getText("error.statement"));
            return "error";
		}
		 
		ArrayList<Transaction> txnList = DataAccess.getTransactionDetails(accNumber,getText("Datafilepath"));
		if (txnList != null)
		{
			setTxnDetails(txnList);
			return "success";
		}
		else
		{
			addActionError(getText("error.summary"));
            return "error";
		}
		
		//String username = (String) ActionContext.getContext().getSession().get("username");
    	//ArrayList<Account> accountList = DataAccess.getUserAccount(username);
    	
    	
        //if (accountList != null) {      	
        	//ActionContext.getContext().getSession().put("userAccount", accountList);
         //   return "success";
        //} else {
        	//addActionError(getText("error.summary"));
            //return "error";
        //}
    }

}
