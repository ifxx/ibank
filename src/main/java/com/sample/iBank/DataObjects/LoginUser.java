package com.sample.iBank.DataObjects;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlType
public class LoginUser  implements Serializable {
	private String userName;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	private String password;
	private String account;
	List<LoginUser> userData;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XmlElement(name="User")
    public List<LoginUser> getUserData() {
        return userData;
    }
    public void setUserData(List<LoginUser> userData) {
        this.userData = userData;
    }
	
		
}
