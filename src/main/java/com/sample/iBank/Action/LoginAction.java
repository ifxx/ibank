package com.sample.iBank.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sample.iBank.DataAccess.*;
import com.sample.iBank.DataObjects.LoginUser;
import org.apache.struts2.interceptor.ServletRequestAware;
import javax.servlet.http.HttpServletRequest;

public class LoginAction extends ActionSupport implements ServletRequestAware {

	private String username;
    private String password;
    
    private HttpServletRequest request;
    
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.request = servletRequest;
 
    }
 
    public String execute() {
    	
    	System.out.println(getText("Datafilepath"));
 
    	boolean isAuth = DataAccess.isLogin(username, password, getText("Datafilepath"));
    	
        if (isAuth) {
        	ActionContext.getContext().getSession().put("username", username);
        	LoginUser lu = DataAccess.getLoginUser(username,getText("Datafilepath"));
        	//ActionContext.getContext().getSession().put("accountNo", lu.getAccount());
            return "success";
        } else {
        	addActionError(getText("error.login"));
            return "error";
        }
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
