package com.prince.fraud.controller;


import com.prince.fraud.model.FraudCheckHistory;
import com.prince.fraud.service.FraudCheckHistoryService;
import com.prince.fraud.vo.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check/")
public class FraudHistoryCheckController {

    @Autowired
    private FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping
    public List<FraudCheckHistory> getAll(){
        return fraudCheckHistoryService.getAllFraudHistories();
    }

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerId") Integer customerId){

        boolean isFraudulentCustomer =  fraudCheckHistoryService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }

}
