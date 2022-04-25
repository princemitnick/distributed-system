package com.prince.customer.repository;

import com.prince.clients.fraud.FraudCheckResponse;
import com.prince.clients.fraud.FraudClient;
import com.prince.clients.notification.NotificationClient;
import com.prince.clients.notification.NotificationRequest;
import com.prince.customer.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
class CustomerRepositoryTest {

    private final String FRAUD_SERVICE_URL = "http://FRAUD-SERVICE/api/v1/fraud-check/";

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private FraudClient fraudClient;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void createCustomer(){
        Customer customer = Customer
                .builder()
                .email("medouard@pih.org")
                .lastName("Edouard")
                .firstName("Magalie")
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()){
            throw  new RuntimeException("fraudster");
        }

        notificationClient.sendNotification(
                new NotificationRequest(customer.getId(), customer.getEmail(),
                        String.format("Hello %s, welcome to princeCode", customer.getLastName()))
        );

        log.info("Customer : "+customer.getId()+" is registered");
        log.info("Notification sent ");
        log.info("Customer : "+customer.getId()+" is "+fraudCheckResponse.isFraudster());



    }

}