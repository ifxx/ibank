package com.sample.iBank.Action;

import java.util.ArrayList;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;

public class TransferOnLoadAction extends ActionSupport implements ServletRequestAware{
	
	private ArrayList<Account> actDetails;
	private ArrayList<Payee> payeeDetails;
	 private HttpServletRequest request;
	    @Override
	    public void setServletRequest(HttpServletRequest servletRequest) {
	        this.request = servletRequest;
	 
	    }
	
	public ArrayList<Payee> getPayeeDetails() {
		return payeeDetails;
	}

	public void setPayeeDetails(ArrayList<Payee> payeeDetails) {
		this.payeeDetails = payeeDetails;
	}

	public ArrayList<Account> getActDetails() {
		return actDetails;
	}

	public void setActDetails(ArrayList<Account> actDetails) {
		this.actDetails = actDetails;
	}

	public String execute() {
		 
		
			String username = (String) ActionContext.getContext().getSession().get("username");
	    	ArrayList<Account> accountList = DataAccess.getUserAccount(username, getText("Datafilepath"));
	    	
	    	
	        if (accountList != null) {      	
	        	setActDetails(accountList);
	        	ArrayList<Payee> pd = DataAccess.getPayeeDetails(username, getText("Datafilepath"));
	        	setPayeeDetails(pd);
	            return "success";
	        } else {
	        	addActionError(getText("error.statementGeneric"));
	            return "error";
	        }
		
		
		
    }

}
