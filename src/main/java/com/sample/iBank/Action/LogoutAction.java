package com.sample.iBank.Action;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.DataAccess;

public class LogoutAction extends ActionSupport {


	
 
    public String execute() {
    		
    	
    	SessionMap session = (SessionMap) ActionContext.getContext().getSession();
    	session.invalidate();
            return "success";
        
    }
 
   
}

