package com.sample.iBank.Common;


import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.sample.iBank.DataObjects.*;

public class AccountDetails {
	
	private String fpath = "";
	public AccountDetails(String path)
	{
		fpath = path;
	}
	
	public ArrayList<Account> getUserAccountDetails(String username)
	{
		ArrayList<Account> ls = null;
		HashMap<String, ArrayList<Account>> userData  = readXMLData();
		if (userData.containsKey(username))
		{
			ls = userData.get(username);
		}
		return ls;
	}
	
	private HashMap<String, ArrayList<Account>> readXMLData()
    {
		HashMap<String, ArrayList<Account>> userData  = new HashMap<String, ArrayList<Account>>();
    	try{
    		
    		File fXmlFile = new File(fpath+ "/UserAccount.xml");
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	
        	NodeList nList = doc.getElementsByTagName("User");
        	for (int temp = 0; temp < nList.getLength(); temp++) {
        		 
        		Node nNode = nList.item(temp);       
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement = (Element) nNode;
        			String uName = eElement.getElementsByTagName("Name").item(0).getTextContent();
        			NodeList accnodeList = eElement.getElementsByTagName("Account");
        			//String aa = ((Element)eElement.getElementsByTagName("Account").item(0)).getElementsByTagName("Balance").item(0).getTextContent();;
        			ArrayList<Account> accList = new ArrayList<Account>();
        			for(int temp2 = 0; temp2 < accnodeList.getLength(); temp2++)
        			{
        				Element aElement = (Element)accnodeList.item(temp2);  
        				if (aElement.getNodeType() == Node.ELEMENT_NODE)        				
        				{
        					
        					Account ac = new Account();
        					ac.setAccNo(aElement.getElementsByTagName("Number").item(0).getTextContent());
        					ac.setBalance(aElement.getElementsByTagName("Balance").item(0).getTextContent());
        					accList.add(ac);
        				}
        				
        			}
         
        			
        			userData.put(uName, accList);
         
        		}
        	}
    	}
    	catch(Exception ex)
    	{
    		
    		System.out.println("Error in Summay Details: " );
    		ex.printStackTrace();
    	}
    	
    	
    	return userData;
    }
    
	

}
