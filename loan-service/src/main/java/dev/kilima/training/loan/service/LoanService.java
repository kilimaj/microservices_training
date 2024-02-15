package dev.kilima.training.loan.service;

import java.util.Optional;

import dev.kilima.training.loan.entity.LoanDetails;

public interface LoanService {
	Optional<Integer> getMaxId();
	String checkLoanStatus(long loanId);
	int applyForLoan(LoanDetails loan);
	
}
