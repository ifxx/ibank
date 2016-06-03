package com.sample.iBank.DataAccess;


import java.util.ArrayList;

import com.sample.iBank.Common.*;
import com.sample.iBank.DataObjects.*;

public class DataAccess {
	
	public static boolean isLogin(String userName, String password, String path)
	{
		boolean isLogin = false;
		
		LoginDetails ld = new LoginDetails(path);
		LoginUser lu = ld.getLoginUserDetails(userName);
		if (lu != null )
		{
			if (lu.getPassword().equals(password))
			{
				isLogin=true;
			}
		}
		return isLogin;
	}
	
	public static LoginUser getLoginUser(String userName, String path)
	{
			
		LoginDetails ld = new LoginDetails(path);
		LoginUser lu = ld.getLoginUserDetails(userName);
		
		return lu;
	}
	
	public static ArrayList<Account> getUserAccount(String userName, String path)
	{
		AccountDetails ad = new AccountDetails(path);
		ArrayList<Account> ua = ad.getUserAccountDetails(userName);
		
		return ua;
	}
	
	public static ArrayList<Payee> getPayeeDetails(String userName, String path)
	{
		TransferFunds ad = new TransferFunds(path);
		ArrayList<Payee> ua = ad.getPayeeDetails(userName);
		
		return ua;
	}
	
	public static ArrayList<Transaction> getTransactionDetails(String accountNumber, String path)
	{
		TransactionDetails td = new TransactionDetails(path);
		ArrayList<Transaction> txnAl = td.getTransactionDetails(accountNumber);
		return txnAl;
	}
	
	public static String updateFunds(String fromAcc, String toAcc, String amt, String path)
	{
		TransferFunds ad = new TransferFunds(path);
		String str = ad.updateTransferFunds(fromAcc, toAcc, amt);
		return str;
	
	}
	
	public static ArrayList<Biller> getBillers( String path)
	{
		BillPay bp = new BillPay(path);
		ArrayList<Biller> bl = bp.getBillerDetails();		
		return bl;
	}
	
	public static String payBill(String fromAcc, String billerName, String amt, String path)
	{
		BillPay bp = new BillPay(path);
		String str = bp.updateTransferFunds(fromAcc, billerName, amt);
		return str;
	
	}
	
	public static String payiBankInvestor(String fromAcc, String amt, String path, String txType)
	{
		TransferFunds ad = new TransferFunds(path);
		String str = ad.updateTransferFundsInvestor(fromAcc, amt, txType);
		
		
		return str;
	
	}
	
	
}
