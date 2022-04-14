package com.prince.fraud.service;

import com.prince.fraud.model.FraudCheckHistory;
import com.prince.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudCheckHistoryServiceImpl implements FraudCheckHistoryService {

    @Autowired
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;


    @Override
    public boolean isFraudulentCustomer(Integer customerId) {

        fraudCheckHistoryRepository.save(
                FraudCheckHistory
                        .builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );


        return false;
    }

    @Override
    public List<FraudCheckHistory> getAllFraudHistories() {
        return fraudCheckHistoryRepository.findAll();
    }
}
