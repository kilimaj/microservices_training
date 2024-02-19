package dev.kilima.training.loan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoanServiceExceptionHandler {
	@ExceptionHandler(value = PanCardNotFoundException.class)
	public ResponseEntity<String> exception(PanCardNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = CreditServiceBreakDownException.class)
	public ResponseEntity<String> exception(CreditServiceBreakDownException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
