package com.sample.web.bank.model;

public class TradeDto {

	public Transaction Transaction;
	public Double MarkUp;
	public Double BPS;
	public Double FinalRate;
	public Double ProfitInWantedCurrency;
	public Double ProfitInSGD;
	
	
	public TradeDto() {
		// TODO Auto-generated constructor stub
	}
	
	public TradeDto(com.sample.web.bank.model.Transaction transaction,
                    Double markUp, Double bPS, Double finalRate,
                    Double profitInWantedCurrency, Double profitInSGD) {
		super();
		Transaction = transaction;
		MarkUp = markUp;
		BPS = bPS;
		FinalRate = finalRate;
		ProfitInWantedCurrency = profitInWantedCurrency;
		ProfitInSGD = profitInSGD;
	}

	public Double getBPS() {
		return BPS;
	}

	public void setBPS(Double bPS) {
		BPS = bPS;
	}

	public Double getProfitInWantedCurrency() {
		return ProfitInWantedCurrency;
	}

	public void setProfitInWantedCurrency(Double profitInWantedCurrency) {
		ProfitInWantedCurrency = profitInWantedCurrency;
	}

	public Transaction getTransaction() {
		return Transaction;
	}
	public void setTransaction(Transaction transaction) {
		Transaction = transaction;
	}
	public Double getMarkUp() {
		return MarkUp;
	}
	public void setMarkUp(Double markUp) {
		MarkUp = markUp;
	}
	public Double getFinalRate() {
		return FinalRate;
	}
	public void setFinalRate(Double finalRate) {
		FinalRate = finalRate;
	}
	public Double getProfitInSGD() {
		return ProfitInSGD;
	}
	public void setProfitInSGD(Double profitInSGD) {
		ProfitInSGD = profitInSGD;
	}

	@Override
	public String toString() {
		return "TradeDto [Transaction=" + Transaction + ", MarkUp=" + MarkUp
				+ ", BPS=" + BPS + ", FinalRate=" + FinalRate
				+ ", ProfitInWantedCurrency=" + ProfitInWantedCurrency
				+ ", ProfitInSGD=" + ProfitInSGD + "]";
	}
	

}
