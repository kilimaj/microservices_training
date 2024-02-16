package dev.kilima.training.creditscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.kilima.training.creditscore.entity.CreditScore;

@Repository
public interface CreditScoreRepo extends JpaRepository<CreditScore, String> {

}
