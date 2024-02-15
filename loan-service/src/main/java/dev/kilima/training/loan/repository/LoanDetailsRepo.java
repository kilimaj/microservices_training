package dev.kilima.training.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDetailsRepo extends JpaRepository<LoanDetailsRepo, Long> {

	@Query(value = "select max(loan.loanId) from LoanDetails loan")
	long getMaxId(long loanId);
	
	@Query(value = "select loan.status from LoanDetails loan where loan.loanId=?1")
	String checkLoanStatus(long loanId);
}
