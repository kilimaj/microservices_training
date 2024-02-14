package dev.kilima.training.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.kilima.training.client.sample.Sample;
import dev.kilima.training.client.sample.SampleRepo;

@RestController
public class MsgController {

	@Autowired
	SampleRepo repo;

	@Value("${msg}")
	private String msg;

	@RequestMapping("/msg")
	public String getMsg() {
		return msg;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestBody Sample sample) {
		repo.save(sample);
		return "created";
	}

}
