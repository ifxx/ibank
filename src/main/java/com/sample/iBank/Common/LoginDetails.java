package com.sample.iBank.Common;

import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sample.iBank.DataObjects.LoginUser;




public class LoginDetails {
	
	private String fpath = "";
	public LoginDetails(String path)
	{
		fpath = path;
	}
	
	
	public LoginUser getLoginUserDetails(String username)
	{
		LoginUser ls = null;
		HashMap<String, LoginUser> userData  = readXMLData();
		if (userData.containsKey(username))
		{
			ls = userData.get(username);
		}
		return ls;
	}
	
	
	
    private HashMap<String, LoginUser> readXMLData()
    {
    	HashMap<String, LoginUser> userData  = new HashMap<String, LoginUser>();
    	try{
    		
    		File fXmlFile = new File(fpath +"/LoginUser.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	
        	NodeList nList = doc.getElementsByTagName("User");
        	for (int temp = 0; temp < nList.getLength(); temp++) {
        		 
        		Node nNode = nList.item(temp);       
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
         
        			Element eElement = (Element) nNode;
         
        			//System.out.println("Staff id : " + eElement.getAttribute("id"));
        			LoginUser lu = new LoginUser();
        			lu.setUserName(eElement.getElementsByTagName("UserName").item(0).getTextContent());
        			lu.setPassword(eElement.getElementsByTagName("Password").item(0).getTextContent());
        			lu.setAccount(eElement.getElementsByTagName("Account").item(0).getTextContent());
        			userData.put(eElement.getElementsByTagName("UserName").item(0).getTextContent(), lu);
         
        		}
        	}
    	}
    	catch(Exception ex)
    	{
    		
    		System.out.println("Error in LoginDetails: " );
    		ex.printStackTrace();
    	}
    	
    	
    	return userData;
    }
    
		
	
}
