package dev.kilima.training.loan.dto;

public class CreditScore {
	private String pancard;
	private String customerName;
	private double creditscore;

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getCreditscore() {
		return creditscore;
	}

	public void setCreditscore(double creditscore) {
		this.creditscore = creditscore;
	}
}
