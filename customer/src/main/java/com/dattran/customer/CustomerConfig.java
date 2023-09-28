package com.dattran.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced // this annotation is required to use service discovery
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
