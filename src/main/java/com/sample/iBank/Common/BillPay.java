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

public class BillPay {
	
	private String fpath = "";
	public BillPay(String path)
	{
		fpath = path;
	}
	
	public ArrayList<Biller> getBillerDetails() {
		
		ArrayList<Biller> bs = readBillerXMLData();
		
		return bs;
	}

	private ArrayList<Biller> readBillerXMLData() {
		ArrayList<Biller> billerData = new ArrayList<Biller>();
		try {

			File fXmlFile = new File(fpath + "/Biller.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			NodeList nList = doc.getElementsByTagName("Biller");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Biller bl = new Biller();
					String bName = eElement.getElementsByTagName("Name")
							.item(0).getTextContent();
					String bAccount = eElement.getElementsByTagName("Account")
					.item(0).getTextContent();
					bl.setBillAccNo(bAccount);
					bl.setName(bName);
					
					billerData.add(bl);

				}
			}
		} catch (Exception ex) {

			System.out.println("Error in Biller Details: ");
			ex.printStackTrace();
		}

		return billerData;
	}

	public String updateTransferFunds(String fromAccount, String billerName,
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
				String txLine = reportDate+",Bill paid towards :"+billerName+" :: Ref:"+refNo+","+amount+",Debit,"+newFromBal;
				BufferedWriter bw = new BufferedWriter(new FileWriter(txFile, true));
				bw.append(System.getProperty("line.separator"));
				bw.append(txLine);
				bw.close();
				
				
				updateStr = "Transaction Successful Bill paid towards "+billerName +" :Reference Number :"+refNo;
						

			}

		} catch (Exception ex) {
			updateStr = "Transaction failed. Please try again";
		}

		return updateStr;
	}

}
