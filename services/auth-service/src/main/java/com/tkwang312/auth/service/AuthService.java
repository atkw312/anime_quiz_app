package com.tkwang312.auth.service;

import com.tkwang312.auth.dataTransferObject.AuthenticationResponse;
import com.tkwang312.auth.dataTransferObject.LoginRequest;
import com.tkwang312.auth.dataTransferObject.RegisterRequest;
import com.tkwang312.auth.model.Role;
import com.tkwang312.auth.model.User;
import com.tkwang312.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // create user, save to database, return token
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        System.out.println("JWT AFTER SUBSTRING: " + jwtToken);
        System.out.println("JWT LENGTH: " + jwtToken.length());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        System.out.println("JWT AFTER SUBSTRING: [" + jwtToken + "]");
        System.out.println("JWT LENGTH: " + jwtToken.length());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}
