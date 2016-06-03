package com.sample.iBank.rest;

import java.util.ArrayList;

import com.sample.iBank.Action.StatementOnLoadAction;
import com.sample.iBank.Action.TransferFundsAction;
import com.sample.iBank.DataObjects.Account;



public class TransactionService {
	
	public static Restaccount getAccount(String userid)
	{
		Restaccount act = new Restaccount(userid, "");
		
		
		ArrayList<Account> accountList = (new StatementOnLoadAction()).getAccountDetails(userid);
		for (int i=0; i<accountList.size(); i++)
		{
			if (act.getAccountno().equals(""))
			{
				act.setAccountno(accountList.get(i).getAccNo());
			}
			else
			{
				act.setAccountno(act.getAccountno()+"|"+accountList.get(i).getAccNo());
			}
			
		}
		
		return act;
	}
	
	/* Status code 200 - Txn Successful 
	 * Status code 451 - Failed transaction
	 * Status code 452 - Failed transaction due to insufficient funds.
	 */
	
	public static int updateAccount(Resttransaction txn)
	{
		int status = 200;
		
		String accno = txn.getAccountno();
		String amt = txn.getAmt();
		String txntype = txn.getTxnType();
		
		System.out.println("aaa" + accno+"  "+amt+"  "+txntype);
		
		TransferFundsAction tf = new TransferFundsAction();
		String msg = tf.restTransaction(accno, amt, txntype);
		System.out.println("msg "+msg);
		
		if (msg.contains("failed"))
		{
			status = 451;
			if (msg.contains("Not enough balance"))
			{
				status = 452;
			}
		}
		
		return status;
	}
	
	public static Resttransaction getTransactionDetails(String id)
	{
		Resttransaction rst = new Resttransaction();
		return rst;
	
	}

}
