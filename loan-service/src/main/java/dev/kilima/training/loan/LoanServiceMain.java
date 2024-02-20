package dev.kilima.training.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import dev.kilima.training.loan.config.CreditScoreProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {CreditScoreProxy.class})
public class LoanServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceMain.class, args);

	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
