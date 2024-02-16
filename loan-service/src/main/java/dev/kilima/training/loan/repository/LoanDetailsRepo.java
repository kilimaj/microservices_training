package dev.kilima.training.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.kilima.training.loan.entity.LoanDetails;

@Repository
public interface LoanDetailsRepo extends JpaRepository<LoanDetails, Long> {

	@Query(value = "select max(loan.loanId) from LoanDetails loan")
	Optional<Long> getMaxId();

	@Query(value = "select loan.loanStatus from LoanDetails loan where loan.loanId=?1")
	String checkLoanStatus(long loanId);

	@Query(value = "select loan from LoanDetails loan where loan.loanId=?1")
	LoanDetails getLoanDetailsById(long loanId);
}
