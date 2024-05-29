package com.dattran;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {
    public void registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.fistName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
    }
}
