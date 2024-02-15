package dev.kilima.training.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ConfigClientExample.class, args);

	}

	@Autowired
	public void setEnvironment(Environment env) {
		System.out.println(env.getProperty("msg"));
	}

}
