package dev.kilima.training.loan.service;

import dev.kilima.training.loan.entity.LoanDetails;

public interface LoanService {
	long getMaxId();
	String checkLoanStatus(long loanId);
	String applyForLoan(LoanDetails loan);
}
