package com.dattran.customer;

import com.dattran.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();

        // todo: check if email valid
        // todo: check if email exists
        // todo: check if fraudster
        try {
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                    "http://localhost:8081/api/v1/fraud-check/{customerId}",
                    FraudCheckResponse.class,
                    customer.getId()
            );
            if(fraudCheckResponse.isFraudster()) {
                throw new IllegalStateException("Fraudster");
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                String responseBody = e.getResponseBodyAsString();
            }
        }


        customerRepository.save(customer);
        // todo: send notification
    }
}
