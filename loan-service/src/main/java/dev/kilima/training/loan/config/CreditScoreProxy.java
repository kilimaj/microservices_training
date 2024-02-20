package dev.kilima.training.loan.config;

import java.util.Optional;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.kilima.training.loan.dto.CreditScore;

/*
 * The following should be used to send request from Feign Client which includes 
 * @FeignClient(name="service name", url="that shows on Eureka terminal"
 */
@FeignClient(name = "creditscore-service", url = "http://localhost:8092/creditscoreapp/creditscore")
@LoadBalancerClient(name = "creditscore-service", configuration = LoadBalanceConfiguration.class)
public interface CreditScoreProxy {
	@GetMapping(value = "/{pancard}")
	Optional<CreditScore> getCreditScore(@PathVariable String pancard);

	@GetMapping("onlyscore/{pancard}")
	String getCreditScoreOnly(@PathVariable String pancard);
}
