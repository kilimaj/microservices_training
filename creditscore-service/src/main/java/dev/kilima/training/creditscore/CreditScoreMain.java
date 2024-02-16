package dev.kilima.training.creditscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CreditScoreMain {

	public static void main(String[] args) {
		SpringApplication.run(CreditScoreMain.class, args);

	}

}
