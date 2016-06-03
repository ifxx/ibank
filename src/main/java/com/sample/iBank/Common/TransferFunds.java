package com.sample.iBank.Common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sample.iBank.DataObjects.*;

public class TransferFunds {
	
	private String fpath = "";
	public TransferFunds(String path)
	{
		fpath = path;
	}

	public ArrayList<Payee> getPayeeDetails(String username) {
		ArrayList<Payee> ls = null;
		HashMap<String, ArrayList<Payee>> userData = readPayeeXMLData();
		if (userData.containsKey(username)) {
			ls = userData.get(username);
		}
		return ls;
	}

	private HashMap<String, ArrayList<Payee>> readPayeeXMLData() {
		HashMap<String, ArrayList<Payee>> userData = new HashMap<String, ArrayList<Payee>>();
		try {

			File fXmlFile = new File(fpath + "/PayeeList.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("User");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String uName = eElement.getElementsByTagName("UserName")
							.item(0).getTextContent();
					NodeList accnodeList = eElement
							.getElementsByTagName("Payee");
					// String aa =
					// ((Element)eElement.getElementsByTagName("Account").item(0)).getElementsByTagName("Balance").item(0).getTextContent();;
					ArrayList<Payee> accList = new ArrayList<Payee>();
					for (int temp2 = 0; temp2 < accnodeList.getLength(); temp2++) {
						Element aElement = (Element) accnodeList.item(temp2);
						if (aElement.getNodeType() == Node.ELEMENT_NODE) {

							Payee py = new Payee();
							py.setName(aElement.getElementsByTagName("Name")
									.item(0).getTextContent());
							py.setPayeeAccNo(aElement
									.getElementsByTagName("Account").item(0)
									.getTextContent());
							accList.add(py);
						}

					}

					userData.put(uName, accList);

				}
			}
		} catch (Exception ex) {

			System.out.println("Error in Payee Details: ");
			ex.printStackTrace();
		}

		return userData;
	}

	public String updateTransferFunds(String fromAccount, String toAccount,
			String amount) {
		String updateStr = "";
		boolean isUpdate = false;
		String newFromBal = "";
		String newToBal = "";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);

		try {
			double tfAmount = Double.parseDouble(amount);
			
			File fXmlFile = new File(fpath + "/UserAccount.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("User");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String uName = eElement.getElementsByTagName("Name")
							.item(0).getTextContent();
					NodeList accnodeList = eElement
							.getElementsByTagName("Account");
					// String aa =
					// ((Element)eElement.getElementsByTagName("Account").item(0)).getElementsByTagName("Balance").item(0).getTextContent();;
					ArrayList<Account> accList = new ArrayList<Account>();
					for (int temp2 = 0; temp2 < accnodeList.getLength(); temp2++) {
						Element aElement = (Element) accnodeList.item(temp2);
						if (aElement.getNodeType() == Node.ELEMENT_NODE) {

							if (aElement.getElementsByTagName("Number").item(0)
									.getTextContent().equals(fromAccount)) {
								double bal = Double.parseDouble(aElement
										.getElementsByTagName("Balance")
										.item(0).getTextContent());
								if (bal < tfAmount) {
									updateStr = "Transaction failed. Not enough balance";
								} else {
									bal = bal - tfAmount;
									isUpdate = true;
									newFromBal = String.format("%10.2f", bal);
									aElement.getElementsByTagName("Balance")
											.item(0).setTextContent(newFromBal);
								}

							}

							if (aElement.getElementsByTagName("Number").item(0)
									.getTextContent().equals(toAccount)) {
								double bal2 = Double.parseDouble(aElement
										.getElementsByTagName("Balance")
										.item(0).getTextContent());
								bal2 = bal2 + tfAmount;
								newToBal = String.format("%10.2f", bal2);
								aElement.getElementsByTagName("Balance")
										.item(0).setTextContent(newToBal);

							}

						}

					}

				}
			}

			if (isUpdate) {
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fXmlFile);
				transformer.transform(source, result);
				
				String refNo =String.valueOf((new Date()).getTime());
				
				String filename = "/"+fromAccount+".csv";
				File txFile = new File(fpath +filename);
				String txLine = reportDate+",Amount debited Ref:"+refNo+","+amount+",Debit,"+newFromBal;
				BufferedWriter bw = new BufferedWriter(new FileWriter(txFile, true));
				bw.append(System.getProperty("line.separator"));
				bw.append(txLine);
				bw.close();
				
				try{
					String tofilename = "/"+toAccount+".csv";
					File totxFile = new File(fpath+ tofilename);
					String totxLine = reportDate+",Amount Credited Ref:"+refNo+","+amount+",Credit,"+newToBal;
					BufferedWriter bw2 = new BufferedWriter(new FileWriter(totxFile, true));
					bw2.append(System.getProperty("line.separator"));
					bw2.append(totxLine);
					bw2.close();
				}
				catch(Exception e3)
				{
				}
				updateStr = "Fund Transfer Successful - Reference Number :"+refNo;

			}

		} catch (Exception ex) {
			updateStr = "Transaction failed. Please try again";
		}

		return updateStr;
	}
	
	
	public String updateTransferFundsInvestor(String fromAccount, String amount, String txType) {
		String updateStr = "";
		String billerName = "iBankInvestor";
		boolean isUpdate = false;
		String newFromBal = "";
		String newToBal = "";
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);

		try {
			double tfAmount = Double.parseDouble(amount);
			
			File fXmlFile = new File(fpath + "/UserAccount.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("User");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String uName = eElement.getElementsByTagName("Name")
							.item(0).getTextContent();
					NodeList accnodeList = eElement
							.getElementsByTagName("Account");
					// String aa =
					// ((Element)eElement.getElementsByTagName("Account").item(0)).getElementsByTagName("Balance").item(0).getTextContent();;
					ArrayList<Account> accList = new ArrayList<Account>();
					for (int temp2 = 0; temp2 < accnodeList.getLength(); temp2++) {
						Element aElement = (Element) accnodeList.item(temp2);
						if (aElement.getNodeType() == Node.ELEMENT_NODE) {

							if (aElement.getElementsByTagName("Number").item(0)
									.getTextContent().equals(fromAccount)) {
								double bal = Double.parseDouble(aElement
										.getElementsByTagName("Balance")
										.item(0).getTextContent());
								if (bal < tfAmount && txType.equalsIgnoreCase("debit")) {
									updateStr = "Transaction failed. Not enough balance";
								} else {
									if (txType.equalsIgnoreCase("debit"))
									{
										bal = bal - tfAmount;
									}
									else
									{
										bal = bal + tfAmount;
									}
									isUpdate = true;
									newFromBal = String.format("%10.2f", bal);
									aElement.getElementsByTagName("Balance")
											.item(0).setTextContent(newFromBal);
								}

							}

							

						}

					}

				}
			}

			if (isUpdate) {
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fXmlFile);
				transformer.transform(source, result);
				
				String refNo =String.valueOf((new Date()).getTime());
				
				String filename = "/"+fromAccount+".csv";
				File txFile = new File(fpath +filename);
				String txLine = "";
				if (txType.equalsIgnoreCase("debit"))
				{
					 txLine = reportDate+",Funds transfered towards :"+billerName+" :: Ref:"+refNo+","+amount+",Debit,"+newFromBal;
				}else
				{
					 txLine = reportDate+",Funds received from :"+billerName+" :: Ref:"+refNo+","+amount+",Credit,"+newFromBal;
				}
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(txFile, true));
				bw.append(System.getProperty("line.separator"));
				bw.append(txLine);
				bw.close();
				
				
				updateStr = "Transaction Successful towards "+billerName +" :Reference Number :"+refNo;
						

			}

		} catch (Exception ex) {
			updateStr = "Transaction failed. Please try again";
			ex.printStackTrace();
		}

		return updateStr;
	}

	
}
