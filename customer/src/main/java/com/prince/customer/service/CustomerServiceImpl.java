package com.prince.customer.service;

import com.prince.clients.fraud.FraudCheckResponse;
import com.prince.clients.fraud.FraudClient;
import com.prince.clients.notification.NotificationClient;
import com.prince.clients.notification.NotificationRequest;
import com.prince.customer.model.Customer;
import com.prince.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final String FRAUD_SERVICE_URL = "http://FRAUD-SERVICE/api/v1/fraud-check/";

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private FraudClient fraudClient;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Customer createCustomer(Customer customer) {

        Optional<Customer> customerOptional =
                customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent())
            throw new RuntimeException("Customer already registered");


        customerRepository.saveAndFlush(customer);



        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse.isFraudster()) {
            throw new RuntimeException("Fraudster");
        }

        notificationClient.sendNotification(
                new NotificationRequest(customer.getId(), customer.getEmail(),
                        String.format("Hello %s, welcome to princeCode", customer.getLastName()))
        );

        log.info("Customer : "+customer.getId()+" is registered");
        log.info("Notification sent ");
        log.info("Customer : "+customer.getId()+" is "+fraudCheckResponse.isFraudster());

        return null;
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {

        Optional<Customer> customer1 = customerRepository.findById(id);

        if (customer1.isEmpty())
            throw new RuntimeException("Customer does not exist");

        if (customer.getEmail() != null
                && customer.getEmail().length() > 0
                && !Objects.equals(customer.getEmail(), customer1.get().getEmail())
        ){
            customer1.get().setEmail(customer.getEmail());
        }
        if (customer.getLastName() != null
                && customer.getLastName().length() > 0
                && !Objects.equals(customer.getLastName(), customer1.get().getLastName())
        ){
            customer1.get().setLastName(customer.getLastName());
        }
        if (customer.getFirstName() != null
                && customer.getFirstName().length() > 0
                && !Objects.equals(customer.getFirstName(), customer1.get().getFirstName())
        ){
            customer1.get().setFirstName(customer.getFirstName());
        }

    }

    @Override
    public Customer getOneCustomer(Integer id) {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer does not exist"));

    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}