package com.tkwang312.auth.service;

import com.tkwang312.auth.dataTransferObject.LoginRequest;
import com.tkwang312.auth.dataTransferObject.RegisterRequest;
import com.tkwang312.auth.model.User;
import com.tkwang312.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder, JwtService jwt){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwt;
    }

    public void register(RegisterRequest registerRequest){
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
//            throw new DuplicateUserException();
        }

        User user = new User();

        user.setUsername(registerRequest.getUsername());

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);
    }


    public String login(LoginRequest request) {

        if (!userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalStateException("Username does not exist");
        }

        User user = userRepository.findByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalStateException("Invalid credentials");
        }

        return jwtService.generateToken(user.getId());
    }


}
