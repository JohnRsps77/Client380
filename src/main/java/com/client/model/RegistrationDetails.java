package com.client.model;

public record RegistrationDetails(String email, String username, String password, String DOB, int profileImageLength, byte[] profileImageData) {
}
