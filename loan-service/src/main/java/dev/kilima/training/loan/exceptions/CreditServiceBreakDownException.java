package dev.kilima.training.loan.exceptions;

public class CreditServiceBreakDownException extends RuntimeException{
	public CreditServiceBreakDownException() {
		super("CreditScore service is down ... unable to process the request");
	}

}
