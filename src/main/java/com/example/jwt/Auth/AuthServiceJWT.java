package com.example.jwt.Auth;


import com.example.jwt.JWT.JwtService;
import com.example.jwt.User.Role;
import com.example.jwt.User.User;
import com.example.jwt.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceJWT {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponseJWT login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user_request = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user_request);
        return AuthResponseJWT.builder()
                .token(token)
                .build();

    }

    public AuthResponseJWT register(RegisterRequest request) {
        User user_request = User.builder()
                .username(request.getUsername())
                .password(passEncoder.encode( request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .role(Role.USER)
                .build();

        userRepository.save(user_request);

        return AuthResponseJWT.builder()
                .token(jwtService.getToken(user_request))
                .build();

    }
}
