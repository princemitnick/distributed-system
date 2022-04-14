package com.prince.fraud.service;

import com.prince.fraud.model.FraudCheckHistory;

import java.util.List;

public interface FraudCheckHistoryService {

    boolean isFraudulentCustomer(Integer customerId);

    List<FraudCheckHistory> getAllFraudHistories();
}
