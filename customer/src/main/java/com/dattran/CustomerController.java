package com.dattran;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("new customer registered {}", customerRequest);
        customerService.registerCustomer(customerRequest);
    }
}
