package dev.kilima.training.loan.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.kilima.training.loan.dto.CreditScore;
import dev.kilima.training.loan.entity.LoanDetails;
import dev.kilima.training.loan.repository.LoanDetailsRepo;
import dev.kilima.training.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDetailsRepo repo;

	@Autowired
	RestTemplate template;

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
			loan.setLoanId(loanId + 2);
		}

		String dateApplied = LocalDate.now().toString();
		loan.setDateApplied(dateApplied);
		loan.setLoanStatus("APPLIED");
		repo.save(loan);
		return loan.getLoanId();
	}

	@Override
	public String loanVerification(long loanId) {
		LoanDetails loandetails = repo.getLoanDetailsById(loanId);
		String pancard = loandetails.getPancard();
		// Connect to CreditScore - service using RestTemplate
		String url = "http://localhost:8092/creditscore/" + pancard;
		CreditScore creditScore = template.getForObject(url, CreditScore.class);
		System.out.println(creditScore);
		loandetails.setCreditscore(creditScore.getCreditscore());
		if (creditScore.getCreditscore() >= 600) {
			loandetails.setLoanStatus("APPROVED");
			loandetails.setDateSanctioned(LocalDate.now().toString());
		} else {
			loandetails.setLoanStatus("REJECTED");
			loandetails.setRemarks("Less Credit Score");
		}

		repo.save(loandetails);
		return "verified";
	}

}
