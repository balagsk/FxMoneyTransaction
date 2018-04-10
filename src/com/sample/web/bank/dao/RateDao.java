package com.sample.web.bank.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.sample.web.bank.model.Rate;
import com.sample.web.bank.model.Transaction;

public class RateDao {
	

	public java.util.List<Rate> getRateDetails(String BaseCurrency, String WantedCurrency){
		String csvFilePath = "../MoneyEx/file/rates.csv";
		BufferedReader br = null;
		String line = "[\\r\\n]";
		String cvsSplitBy = ",";
		 java.util.List<Rate> list=new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {
				String[] dataArr = line.split(cvsSplitBy);
				if(dataArr[0].equals(BaseCurrency) && dataArr[1].equals(WantedCurrency)){
					Rate rate=new Rate();
					rate.setBaseCurrency(dataArr[0]);
					rate.setWantedCurrency(dataArr[1]);
					rate.setBasisPoints(dataArr[2]);
					rate.setTransactionTime(dataArr[3]);
					System.out.println(rate.toString());
					list.add(rate);
				}
				else{
				}
			}
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
		//System.out.println("second "+rate.toString());
		return list;
	}
	
	public Rate getRateDetails(String BaseCurrency,String WantedCurrency,String  TransactionTime){
		//return the Input Rate details based on BaseCurrency/WantedCurrency/TransactionTime
		String csvFilePath = "../MoneyEx/file/rates.csv";
		BufferedReader br = null;
		String line = "[\\r\\n]";
		String cvsSplitBy = ",";
		Rate rate=new Rate();
		try {
			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {
				String[] dataArr = line.split(cvsSplitBy);
				if(dataArr[0].equals(BaseCurrency) && dataArr[1].equals(WantedCurrency) && dataArr[3].equals(TransactionTime)){
					rate.setBaseCurrency(dataArr[0]);
					rate.setWantedCurrency(dataArr[1]);
					rate.setBasisPoints(dataArr[2]);
					rate.setTransactionTime(dataArr[3]);
					//System.out.println(rate.toString());
				}
				else{
				}
			}

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
		//System.out.println("second "+rate.toString());
		return rate;

	}
	
public Rate getNearestTime(String WantedCurrency,Transaction txnModel) throws ParseException{
	
	Rate rateObj=null;
	RateDao rateDao=new RateDao();
	List<Rate> listRate=rateDao.getRateDetails(txnModel.getBaseCurrency(), WantedCurrency);
	TreeSet<LocalTime> times = new TreeSet<> ();
	for (Rate Rate : listRate) {
		times.add(LocalTime.parse(Rate.getTransactionTime()));
	}
	 SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
	    java.util.Date time =(java.util.Date)format.parse(txnModel.getTransactionTime());
	    java.sql.Time sqlTime = new java.sql.Time(time.getTime());
	
	LocalTime ceiling = times.ceiling(LocalTime.parse(sqlTime.toString()));
	if (ceiling != null){
		System.out.println("ceiling "+ceiling);
		rateObj=rateDao.getRateDetails(txnModel.getBaseCurrency(),WantedCurrency, ceiling.toString());
	}
	else{
		return rateObj;
	}
	return rateObj;
}



public Rate getNearestTimeForSGDProfit(String desiredCurrency,Transaction txnModel) throws ParseException{
	
	Rate rateObj=null;
	RateDao rateDao=new RateDao();
	List<Rate> listRate=rateDao.getRateDetails(txnModel.getWantedCurrency(), desiredCurrency);
	TreeSet<LocalTime> times = new TreeSet<> ();
	for (Rate Rate : listRate) {
		times.add(LocalTime.parse(Rate.getTransactionTime()));
	}
	 SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
	    java.util.Date time =(java.util.Date)format.parse(txnModel.getTransactionTime());
	    java.sql.Time sqlTime = new java.sql.Time(time.getTime());
	    System.out.println("sqlTime : "+sqlTime);
	LocalTime ceiling = times.ceiling(LocalTime.parse(sqlTime.toString()));
	if (ceiling != null){
		System.out.println("ceiling "+ceiling);
		rateObj=rateDao.getRateDetails(txnModel.getWantedCurrency(),desiredCurrency, ceiling.toString());
		System.out.println("rateObj : "+rateObj);
	}
	else{
		return rateObj;
	}
	return rateObj;
}
	
	
	public static void main(String[] args) throws ParseException {
		RateDao rd=new RateDao();
		//CNY	SGD	0.2012	9:00
		// SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
		    // or
		    SimpleDateFormat format = new SimpleDateFormat("HH:mm"); // 12 hour format
		    java.util.Date d1 =(java.util.Date)format.parse("8:50");
		    java.sql.Time sqlTime = new java.sql.Time(d1.getTime());
		//List<Rate> listRate=rd.getRateDetails("CNY", "SGD");
		/*TreeSet<LocalTime> times = new TreeSet<> ();
		for (Rate Rate : listRate) {
			times.add(LocalTime.parse(Rate.getTransactionTime()));
		}
		
		LocalTime ceiling = times.ceiling(LocalTime.parse("08:52"));
		if (ceiling != null){
			System.out.println("ceiling "+ceiling);
		}*/
	}

}
