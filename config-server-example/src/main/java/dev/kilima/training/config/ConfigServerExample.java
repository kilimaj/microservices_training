package dev.kilima.training.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ConfigServerExample.class, args);
	}

}
