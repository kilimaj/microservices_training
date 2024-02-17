package dev.kilima.training.creditscore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kilima.training.creditscore.entity.CreditScore;
import dev.kilima.training.creditscore.repository.CreditScoreRepo;
import dev.kilima.training.creditscore.service.CreditScoreService;

@Service
public class CreditScoreServiceImpl implements CreditScoreService{

	@Autowired
	CreditScoreRepo repo;
	
	@Override
	public void add(CreditScore credit) {
		repo.save(credit);
	}

	@Override
	public List<CreditScore> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<CreditScore> getCreditScore(String pancard) {
		Optional<CreditScore> creditscore = repo.findById(pancard);
		return creditscore;
	}

	@Override
	public Optional<Double> getScoreByPancard(String pancard) {
		Optional<CreditScore> creditScore = repo.findById(pancard);
		double score = creditScore.get().getCreditscore();
		return Optional.of(score);
	}
	
}
