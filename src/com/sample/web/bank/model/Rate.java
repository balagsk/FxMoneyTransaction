package com.sample.web.bank.model;

public class Rate {
	
	public String BaseCurrency;
	public String WantedCurrency;
	public String BasisPoints;
	public String TransactionTime;
	
	
	public Rate() {
		// TODO Auto-generated constructor stub
	}
	
	public Rate(String baseCurrency, String wantedCurrency, String basisPoints,
			String transactionTime) {
		super();
		BaseCurrency = baseCurrency;
		WantedCurrency = wantedCurrency;
		BasisPoints = basisPoints;
		TransactionTime = transactionTime;
	}

	public String getBaseCurrency() {
		return BaseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		BaseCurrency = baseCurrency;
	}
	public String getWantedCurrency() {
		return WantedCurrency;
	}
	public void setWantedCurrency(String wantedCurrency) {
		WantedCurrency = wantedCurrency;
	}
	public String getBasisPoints() {
		return BasisPoints;
	}
	public void setBasisPoints(String basisPoints) {
		BasisPoints = basisPoints;
	}
	public String getTransactionTime() {
		return TransactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}
	
	@Override
	public String toString() {
		return "Rate [BaseCurrency=" + BaseCurrency + ", WantedCurrency="
				+ WantedCurrency + ", BasisPoints=" + BasisPoints
				+ ", TransactionTime=" + TransactionTime + "]";
	}
	
}
