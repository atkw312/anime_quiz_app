package com.tkwang312.auth.controller;

import com.tkwang312.auth.dataTransferObject.LoginRequest;
import com.tkwang312.auth.dataTransferObject.RegisterRequest;
import com.tkwang312.auth.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //get html page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "index";
    }

    // submit signup
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest) {

        authService.register(registerRequest);

        return "redirect:/auth/login";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginRequest loginRequest
    ) {
        try {
            String token = authService.login(loginRequest);

            System.out.println("Successfully logged in as " + loginRequest.getUsername());
            System.out.println("token is: " + token);

            return "redirect:/auth/success";

        } catch (IllegalStateException e) {

            System.out.println("Invalid username or password");

            return "redirect:/auth/login";
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}
