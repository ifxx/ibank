package com.sample.iBank.Action;

import java.util.ArrayList;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;

public class SummaryAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.request = servletRequest;
 
    }

	public String execute() {
		 
		String username = (String) ActionContext.getContext().getSession().get("username");
    	ArrayList<Account> accountList = DataAccess.getUserAccount(username, getText("Datafilepath"));
    	
    	
        if (accountList != null) {      	
        	ActionContext.getContext().getSession().put("userAccount", accountList);
            return "success";
        } else {
        	addActionError(getText("error.summary"));
            return "error";
        }
    }
	
	
	
	

}
