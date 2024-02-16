package dev.kilima.training.creditscore.service;

import java.util.List;
import java.util.Optional;

import dev.kilima.training.creditscore.entity.CreditScore;

public interface CreditScoreService {
	void add(CreditScore credit);
	List<CreditScore> findAll();
	Optional<CreditScore> getCreditScore(String pancard);
}
