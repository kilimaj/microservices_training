package dev.kilima.training.loan.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kilima.training.loan.entity.LoanDetails;
import dev.kilima.training.loan.repository.LoanDetailsRepo;
import dev.kilima.training.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDetailsRepo repo;

	@Override
	public Optional<Long> getMaxId() {
		// TODO Auto-generated method stub
		return repo.getMaxId();
	}

	@Override
	public Optional<LoanDetails> checkLoanStatus(long loanId) {
		Optional<LoanDetails> details = repo.findById(loanId);
		return details;
	}

	@Override
	public long applyForLoan(LoanDetails loan) {
		Optional<Long> id = getMaxId();
		if (id.isEmpty())
			loan.setLoanId(1000);
		else {
			int loanId = id.get().intValue();
			loan.setLoanId(loanId+2);
		}
		
		String dateApplied = LocalDate.now().toString();
		loan.setDateApplied(dateApplied);
		loan.setLoanStatus("APPLIED");
		repo.save(loan);
		return loan.getLoanId();
	}

}
