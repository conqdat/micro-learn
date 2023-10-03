package com.dattran.customer;

import com.dattran.amqp.RabbitMQMessageProducer;
import com.dattran.clients.fraud.FraudCheckResponse;
import com.dattran.clients.fraud.FraudClient;
import com.dattran.clients.notification.NotificationClient;
import com.dattran.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);

        // todo: check if email valid
        // todo: check if email exists
        // todo: check if fraudster
        try {
            FraudCheckResponse fraudCheckResponse = fraudClient.checkFraud(customer.getId());
            if(fraudCheckResponse.isFraudster()) {
                throw new IllegalStateException("Fraudster");
            }
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                String responseBody = e.getResponseBodyAsString();
            }
        }


        // todo: make it async (send notification)
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getFirstName(),
                String.format("Welcome %s %s", customer.getFirstName(), customer.getLastName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification"
        );
    }
}
