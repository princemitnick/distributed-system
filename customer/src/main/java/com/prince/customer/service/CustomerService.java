package com.prince.customer.service;

import com.prince.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    void updateCustomer(Integer id, Customer customer);
    Customer getOneCustomer(Integer id);
    List<Customer> getAllCustomers();

}