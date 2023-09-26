package com.dattran.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerRegistrationRequest(
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("email")
        String email
) {
}
