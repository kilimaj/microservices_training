package dev.kilima.training.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dev.kilima.training.loan.entity.LoanDetails;
import dev.kilima.training.loan.repository.LoanDetailsRepo;
import dev.kilima.training.loan.service.LoanService;

public class LoanServiceImpl implements LoanService{

	@Autowired 
	LoanDetailsRepo repo;
	
	@Override
	public long getMaxId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String checkLoanStatus(long loanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String applyForLoan(LoanDetails loan) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
