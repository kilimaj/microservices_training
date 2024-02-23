package dev.kilima.training.logging.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Slf4jController {
	private static final Logger logger = LoggerFactory.getLogger(Slf4jController.class);

	@RequestMapping("/hello")
	public String hello() {
		List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
		logger.info("Hello from Logback {}", data);
		return data.toString();
	}
}
