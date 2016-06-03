package com.sample.iBank.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;



import com.sample.iBank.DataObjects.*;

public class TransactionDetails {
	
	private String fpath = "";
	public TransactionDetails(String path)
	{
		fpath = path;
	}
	
		public ArrayList<Transaction> getTransactionDetails (String account)
		{
			ArrayList<Transaction> txList = new ArrayList<Transaction>();
			try
			{
				String filename = "/"+account+".csv";
				File txFile = new File(fpath + filename);
				BufferedReader br = new BufferedReader(new FileReader(txFile));
				String line = "";
				int count = 0;
				while((line=br.readLine())!= null)
				{
					count = count + 1;
					if (count == 1)
						continue;
					try
					{
						Transaction txn = new Transaction();
						String[] parts = line.split(",");
						
						txn.setTxDate(parts[0]);
						txn.setDetails(parts[1]);
						txn.setTxAmount(parts[2]);
						txn.setTxType(parts[3]);
						txn.setBalance(parts[4]);
						txList.add(txn);
					}
					catch(Exception ex1)
					{
						continue;
					}
					
				}
			}
			catch(Exception ex)
			{
				
			}
			
			
			return txList;
		}

}
