package dev.kilima.training.creditscore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CreditScore {
	@Id
	@Column(length = 10)
	private String pancard;

	@Column(length = 40)
	private String customerName;

	@Column
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
