package com.prince.customer.repository;

import com.prince.customer.model.Customer;
import com.prince.customer.repository.CustomerRepository;
import com.prince.customer.vo.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CustomerRepositoryTest {

    private final String FRAUD_SERVICE_URL = "http://localhost:9091/api/v1/fraud-check/";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void createCustomer(){
        Customer customer = Customer
                .builder()
                .email("prstanley@pih.org")
                .lastName("Jean Baptiste")
                .firstName("Prince")
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                FRAUD_SERVICE_URL+"/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse.isFraudster()){
            throw  new RuntimeException("fraudster");
        }


    }

}