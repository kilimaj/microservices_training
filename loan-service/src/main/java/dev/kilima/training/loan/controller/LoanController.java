package dev.kilima.training.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
