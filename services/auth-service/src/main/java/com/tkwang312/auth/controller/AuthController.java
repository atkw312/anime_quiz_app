package com.tkwang312.auth.controller;

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

        return "redirect:/auth/register";
    }
}
