package com.dattran;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    public record FraudCheckResponse(Boolean isFraudster) {
    }

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8080/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster check failed");
        }
    }
}
