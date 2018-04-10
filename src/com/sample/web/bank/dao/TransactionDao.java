package com.sample.web.bank.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sample.web.bank.model.Transaction;

public class TransactionDao {
	
	public Transaction getTxnDetails(String BaseCurrency, String WantedCurrency, String  TransactionTime){
		//return the Input Rate details based on BaseCurrency/WantedCurrency/TransactionTime
		return null;
	}
	
	public List<Transaction> getTxnDetails(){
		String csvFilePath = "../MoneyEx/file/transactions.csv";
		BufferedReader br = null;
		String line = "[\\r\\n]";
		String cvsSplitBy = ",";
		List<Transaction> list=new ArrayList<>();
		try {
			
			br = new BufferedReader(new FileReader(csvFilePath));
			/*if(br.readLine()!=null)
				br.readLine();*/
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] dataArr = line.split(cvsSplitBy);
					Transaction transaction = new Transaction();
					transaction.setBaseCurrency(dataArr[0]);
					transaction.setWantedCurrency(dataArr[1]);
					transaction.setAmount(dataArr[2]);
					transaction.setClientType(dataArr[3]);
					transaction.setTransactionTime(dataArr[4]);
					System.out.println(transaction.toString());
					list.add(transaction);
				

			}
			System.out.println("Total record size : "+list.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	
		return list;
	}
	
	
	public static void main(String[] args) {
		TransactionDao td=new TransactionDao();
		td.getTxnDetails();
	}

}
