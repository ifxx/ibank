package com.sample.iBank.Action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

public class BillerOnLoadAction extends ActionSupport implements ServletRequestAware {

	private ArrayList<Biller> billerDetails;
	private ArrayList<Account> actDetails;
	
	 private HttpServletRequest request;
	    @Override
	    public void setServletRequest(HttpServletRequest servletRequest) {
	        this.request = servletRequest;
	 
	    }

	public ArrayList<Biller> getBillerDetails() {
		return billerDetails;
	}

	public void setBillerDetails(ArrayList<Biller> billerDetails) {
		this.billerDetails = billerDetails;
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
        	ArrayList<Biller> billerList = DataAccess.getBillers(getText("Datafilepath"));
        	setBillerDetails(billerList);
            return "success";
        } else {
        	addActionError(getText("error.statementGeneric"));
            return "error";
        }
	
	
	
}

}
