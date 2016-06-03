package com.sample.iBank.Action;

import java.util.ArrayList;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;

public class StatementOnLoadAction extends ActionSupport implements ServletRequestAware {
	
	 private HttpServletRequest request;
	    @Override
	    public void setServletRequest(HttpServletRequest servletRequest) {
	        this.request = servletRequest;
	 
	    }

	public String execute() {
		 
		if (ActionContext.getContext().getSession().get("userAccount")!=null){
			return "success";
		}
		else
		{
			String username = (String) ActionContext.getContext().getSession().get("username");
	    	
			ArrayList<Account> accountList = getAccountDetails(username);
	    	
	        if (accountList != null) {      	
	        	ActionContext.getContext().getSession().put("userAccount", accountList);
	            return "success";
	        } else {
	        	addActionError(getText("error.statementGeneric"));
	            return "error";
	        }
		}
		
		
    }
	
	public ArrayList<Account> getAccountDetails(String username)
	{
		System.out.println(username);
		System.out.println ( getText("Datafilepath"));
		ArrayList<Account> accountList = DataAccess.getUserAccount(username, getText("Datafilepath"));
		return accountList;
	}
	
	

}
