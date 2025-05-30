package com.example.jwt.Endpoint;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api_rest/")
@RequiredArgsConstructor
public class EndPointController {

    @PostMapping("welcome")
    public String welcome(Authentication authentication) {
    return "Welcome, " + authentication.getName();
}


    
}

