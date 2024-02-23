package dev.kilima.training.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Slf4JLoggingMain {
	private static final Logger logger = LoggerFactory.getLogger(Slf4JLoggingMain.class);

	public static void main(String[] args) {
		SpringApplication.run(Slf4JLoggingMain.class, args);
		logger.info(" ** Spring-Boot-Self4j-Main started ... ");
	}

}
