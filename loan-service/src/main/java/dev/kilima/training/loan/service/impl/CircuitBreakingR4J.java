package dev.kilima.training.loan.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.kilima.training.loan.config.CreditScoreProxy;
import dev.kilima.training.loan.dto.CreditScore;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CircuitBreakingR4J {
	@Autowired
	CreditScoreProxy feign;
	
	@CircuitBreaker(name = "creditscore-service", fallbackMethod = "getOnlyScoreWithR4jFallBack")
	public Optional<CreditScore> getCreditScore(String pancard) {
		System.out.println("Inside Delegate class FromProxy()");
		Optional<CreditScore> score = feign.getCreditScore(pancard);
		return score;
	}
	
	//This is the fallback method used in Circuit breaking
	public Optional<CreditScore> getOnlyScoreWithR4jFallBack(String pancard, Exception ex) {
		System.out.println("credit score service is down!!!... fallback route enable...."
				+ ex.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response from CreditScore " 
				+ "service at this moment. Service will be back shortly " + new Date());
		//throw new CreditServiceBreakDownException(ex.getMessage());
		
		return Optional.of(new CreditScore());
	}
}
