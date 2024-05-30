package com.dattran;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}