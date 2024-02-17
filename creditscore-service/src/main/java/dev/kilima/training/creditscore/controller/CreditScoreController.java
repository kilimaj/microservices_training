package dev.kilima.training.creditscore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.kilima.training.creditscore.entity.CreditScore;
import dev.kilima.training.creditscore.service.CreditScoreService;

@RestController
@RequestMapping("/creditscore")
public class CreditScoreController {
	@Autowired
	CreditScoreService service;

	@PostMapping
	public ResponseEntity<String> add(@RequestBody CreditScore score) {
		service.add(score);
		return new ResponseEntity<String>("inserted", HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CreditScore>> findAll() {
		List<CreditScore> list = service.findAll();
		return new ResponseEntity<List<CreditScore>>(list, HttpStatus.OK);
	}

	@GetMapping("/{pancard}")
	public ResponseEntity<Optional<CreditScore>> getCreditScore(@PathVariable String pancard) {
		Optional<CreditScore> score = service.getCreditScore(pancard);
		return new ResponseEntity<Optional<CreditScore>>(score, HttpStatus.OK);
	}
	
	@GetMapping("onlyscore/{pancard}")
	public ResponseEntity<Optional<Double>> getCreditScoreOnly(@PathVariable String pancard) {
		Optional<Double> score = service.getScoreByPancard(pancard);
		return new ResponseEntity<Optional<Double>>(score, HttpStatus.OK);
	}
}
