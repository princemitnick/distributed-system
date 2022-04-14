package com.prince.customer.controller;

import com.prince.customer.model.Customer;
import com.prince.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/one/{id}")
    public Customer getOne(@PathVariable("id") Integer id){
        return customerService.getOneCustomer(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
    }
}