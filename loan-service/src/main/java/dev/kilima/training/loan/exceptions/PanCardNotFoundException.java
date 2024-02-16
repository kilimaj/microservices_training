package dev.kilima.training.loan.exceptions;

public class PanCardNotFoundException extends RuntimeException{
	public PanCardNotFoundException() {
		super("CreditScore object returned null with existing Pancard...");
	}
}
