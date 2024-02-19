package dev.kilima.training.loan.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.kilima.training.loan.dto.CreditScore;
import dev.kilima.training.loan.entity.LoanDetails;
import dev.kilima.training.loan.exceptions.CreditServiceBreakDownException;
import dev.kilima.training.loan.exceptions.PanCardNotFoundException;
import dev.kilima.training.loan.repository.CreditScoreProxy;
import dev.kilima.training.loan.repository.LoanDetailsRepo;
import dev.kilima.training.loan.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDetailsRepo repo;

	@Autowired
	RestTemplate template;

	@Autowired
	CreditScoreProxy feign;

	@Autowired
	CircuitBreakingR4J r4j;

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
		// String url = "http://localhost:8092/creditscore/" + pancard;
		// CreditScore creditScore = template.getForObject(url, CreditScore.class);

		/*
		 * // Using Feign Client Optional<CreditScore> creditScore =
		 * feign.getCreditScore(pancard);
		 * 
		 * System.out.println("using optional " + creditScore); CreditScore score =
		 * creditScore.get(); // casting from optional to concrete class
		 * System.out.println("after casting " + score);
		 * 
		 * if (score == null) throw new PanCardNotFoundException();
		 * loandetails.setCreditscore(score.getCreditscore()); if
		 * (score.getCreditscore() >= 600) { loandetails.setLoanStatus("APPROVED");
		 * loandetails.setDateSanctioned(LocalDate.now().toString()); } else {
		 * loandetails.setLoanStatus("REJECTED");
		 * loandetails.setRemarks("Less Credit Score"); }
		 */

		// Using Feign Client
		/*
		 * Optional<Double> creditScore = feign.getCreditScoreOnly(pancard);
		 * 
		 * System.out.println("using optional " + creditScore); Double score =
		 * creditScore.get(); // casting from optional to concrete class
		 * System.out.println("after casting " + score);
		 * 
		 * if (score == null) throw new PanCardNotFoundException();
		 * loandetails.setCreditscore(score); if (score >= 600) {
		 * loandetails.setLoanStatus("APPROVED");
		 * loandetails.setDateSanctioned(LocalDate.now().toString()); } else {
		 * loandetails.setLoanStatus("REJECTED");
		 * loandetails.setRemarks("Less Credit Score"); }
		 */

		// Using Circuit with Resilience4j execute the following code
		Optional<CreditScore> r4jscore = r4j.getCreditScore(pancard);
		System.out.println("using optional from circuit breaking " + r4jscore);
		CreditScore score = r4jscore.get(); //casting from Optional to Concrete class
		System.out.println("after casting "+ score);
		
		if (score.getPancard()==null)
			throw new CreditServiceBreakDownException();//when using circuit breaking
		loandetails.setCreditscore(score.getCreditscore());
		if (score.getCreditscore()>600) {
			loandetails.setLoanStatus("APPROVED");
			loandetails.setDateSanctioned(LocalDate.now().toString());
		} else {
			loandetails.setLoanStatus("REJECTED");
			loandetails.setRemarks("Less Credit Score");
		}
		repo.save(loandetails);
		// repo.save(loandetails);
		return "verified";
	}

}
