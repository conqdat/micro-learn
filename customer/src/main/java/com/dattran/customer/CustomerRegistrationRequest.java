package com.dattran.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
