package com.prince.fraud.repository;

import com.prince.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
