package com.sample.web.bank.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import com.sample.web.bank.dao.RateDao;
import com.sample.web.bank.dao.TransactionDao;
import com.sample.web.bank.model.Rate;
import com.sample.web.bank.model.TradeDto;
import com.sample.web.bank.model.Transaction;

public class TransactionService {
	
	public static Double USDamount;
	public static Double Finalrate;
	public static Double ProfitInBccy;
	public static Double ProfitInSGD;
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

	public static double convertCurrency(double baseCurrency,double bps)
	{
		double wantedCurrency=baseCurrency*bps;
		return wantedCurrency;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println("Transaction Serice..");
		Transaction txnModel=new Transaction();
		RateDao rateDao=new RateDao();
		TransactionDao txnDao=new TransactionDao();
		TradeDto tradeDto=new TradeDto();
		
		List<Transaction> txnlist=txnDao.getTxnDetails();
		for (Transaction transaction : txnlist) {
			txnModel.setBaseCurrency(transaction.getBaseCurrency());
			txnModel.setWantedCurrency(transaction.getWantedCurrency());
			txnModel.setAmount(transaction.getAmount());
			txnModel.setClientType(transaction.getClientType());
			txnModel.setTransactionTime(transaction.getTransactionTime());
			
		//If BaseCurrency is not USD, continue to find the profit rate		
			if(txnModel.getBaseCurrency()!="USD"){
				//Rate rateObj=rateDao.getRateDetails(txnModel.getBaseCurrency(),"USD", txnModel.getTransactionTime());
				Rate rateObj=rateDao.getNearestTime("USD", txnModel);
				System.out.println("BPS Value : "+rateObj.getBasisPoints());
				Double bps=Double.parseDouble(rateObj.getBasisPoints());
				Double TxnAmount=Double.parseDouble(txnModel.getAmount());
				USDamount=(double) (bps * TxnAmount);
				System.out.println("Converted - USD amount : " + USDamount);
			}else{
				USDamount=Double.parseDouble(txnModel.getAmount());
				System.out.println("Direct - USD amount : " + USDamount);
			}
			
			tradeDto.setTransaction(txnModel);
			//ClientType check
			if(txnModel.getClientType().equalsIgnoreCase("Individual")){
				if(USDamount>0 && USDamount<=8000){
					tradeDto.setMarkUp(0.40);
				}
				else if(USDamount>8000 && USDamount<=20000){
					tradeDto.setMarkUp(0.35);
				}
				else if(USDamount>20000 && USDamount<=35000){
					tradeDto.setMarkUp(0.30);
				}
				else if(USDamount>35000){
					tradeDto.setMarkUp(0.20);
				}
				else{
					System.out.println("System error =========>");
				}
				System.out.println("Final - Mark up value [Individual] :" +tradeDto.getMarkUp());
			}
			else if(txnModel.getClientType().equalsIgnoreCase("Corporate")){
				if(USDamount>0 && USDamount<=1000000){
					tradeDto.setMarkUp(0.15);
				}
				else if(USDamount>1000000 && USDamount<=3000000){
					tradeDto.setMarkUp(0.10);
				}
				else if(USDamount>3000000){
					tradeDto.setMarkUp(0.05);
				}
				else{
					System.out.println("System error =========>");
				}
				System.out.println("Final - Mark up value[Corporate] :" +tradeDto.getMarkUp());
			}
			Rate rateObj02=rateDao.getNearestTime(txnModel.WantedCurrency, txnModel);
			double bps=Double.parseDouble(rateObj02.getBasisPoints());
			System.out.println("BPS : "+bps);
			System.out.println("MarkUp : "+tradeDto.getMarkUp());
			
			DecimalFormat format2 = new DecimalFormat("#.00");
			DecimalFormat format4 = new DecimalFormat("#.0000");
			
			// Final Rate
			Double rate= (bps * (1 - ((tradeDto.getMarkUp()) / (100) )));
			Finalrate= (Double)format4.parse(format4.format(rate)) ;
			System.out.println("\nFinal Profitrate  : " + Finalrate);
			
			//Profit 
			Double TxnAmountObj=Double.parseDouble(txnModel.getAmount());
			Double profitInBccy= (((bps )-(Finalrate))*(TxnAmountObj));
			ProfitInBccy= round(profitInBccy, 2);
			tradeDto.setBPS(bps);
			tradeDto.setFinalRate(Finalrate);
			tradeDto.setProfitInWantedCurrency(ProfitInBccy);
			System.out.println("ProfitInBccy  : " +tradeDto.getProfitInWantedCurrency()+"\n");
			
			if(!txnModel.getWantedCurrency().equals("SGD")){
				
				Rate rateObj03=rateDao.getNearestTimeForSGDProfit("SGD", txnModel);
				System.out.println("Bps string : "+rateObj03.getBasisPoints());
				float bps03=Float.parseFloat(rateObj03.getBasisPoints());
				System.out.println("BPS : "+bps03);
				System.out.println("MarkUp : "+tradeDto.getMarkUp());
				// Final Rate
				Double rateObj04= (bps03 * (1 - ((tradeDto.getMarkUp()) / (100) )));
				Double finalrate02= (Double)format4.parse(format4.format(rateObj04)) ;
				System.out.println("\nFinal Profitrate  : " + finalrate02);
				//Profit 
				Double profit= ((bps03 )*(tradeDto.getProfitInWantedCurrency()));
				//Double profit=(((bps03 )-(finalrate02))*(tradeDto.getProfitInWantedCurrency()));
				ProfitInSGD= round(profit, 2);
				tradeDto.setProfitInSGD(ProfitInSGD);
				System.out.println("ProfitInSGD  : " +tradeDto.getProfitInSGD()+"\n");
				
			}else{
				tradeDto.setProfitInSGD(ProfitInBccy);
				System.out.println("ProfitInSGD  : " +tradeDto.getProfitInSGD()+"\n");
			}
			
			
			System.out.println("Profit & loss Report : "+tradeDto.toString());
			System.out.println("======================================================");
		}
		
		
	}
}
