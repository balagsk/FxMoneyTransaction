package com.sample.web.bank.model;

public class Transaction {

	public String BaseCurrency;
	public String WantedCurrency;
	public String Amount;
	public String ClientType;
	public String TransactionTime;
	
	public Transaction() {
	}
	
	public Transaction(String baseCurrency, String wantedCurrency,
			String amount, String clientType, String transactionTime) {
		super();
		BaseCurrency = baseCurrency;
		WantedCurrency = wantedCurrency;
		Amount = amount;
		ClientType = clientType;
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
	public String getClientType() {
		return ClientType;
	}
	public void setClientType(String clientType) {
		ClientType = clientType;
	}
	public String getTransactionTime() {
		return TransactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}


	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [BaseCurrency=" + BaseCurrency
				+ ", WantedCurrency=" + WantedCurrency + ", ConvertedCurrency="
				+ Amount + ", ClientType=" + ClientType
				+ ", TransactionTime=" + TransactionTime + "]";
	}
	
}
