package dev.kilima.training.loan.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kilima.training.loan.entity.LoanDetails;
import dev.kilima.training.loan.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {
	@Autowired
	LoanService loanService;

	@PostMapping
	public ResponseEntity<String> applyLoan(@RequestBody LoanDetails loan) {
		long id = loanService.applyForLoan(loan);
		String mesg = "Applied ... Loan ID is " + id;
		return new ResponseEntity<String>(mesg, HttpStatus.OK);
	}

	@GetMapping("/checkstatus/{loanId}")
	public ResponseEntity<String> checkLoanStatus(@PathVariable long loanId) {
		Optional<LoanDetails> details = loanService.checkLoanStatus(loanId);

		String status = details.get().getLoanStatus();
		if (status.equals("REJECTED"))
			status = status.concat(" " + details.get().getRemarks());
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@GetMapping("/verify/{loanId}")
	public ResponseEntity<String> loanVerification(@PathVariable int loanId) {
		String msg = loanService.loanVerification(loanId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}
