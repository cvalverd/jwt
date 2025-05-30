package com.example.jwt.JWT;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class JwtServiceTest {

    public static void main(String[] args) {
        JwtService jwtService = new JwtService();

        // Create a dummy user (implementing UserDetails)
        UserDetails user = new User("testuser", "password", Collections.emptyList());

        // Generate a JWT token
        String token = jwtService.getToken(user);
        System.out.println("Generated Token:\n" + token);

        // Validate the token and extract username
        boolean isValid = jwtService.isTokenValid(token, user);
        System.out.println("Is token valid? " + isValid);

        String username = jwtService.getUsernameFromToken(token);
        System.out.println("Extracted username from token: " + username);
    }
}
