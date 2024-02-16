package dev.kilima.training.loan.service;

import java.util.Optional;

import dev.kilima.training.loan.entity.LoanDetails;

public interface LoanService {
	Optional<Long> getMaxId();
	Optional<LoanDetails> checkLoanStatus(long loanId);
	long applyForLoan(LoanDetails loan);
	
}
