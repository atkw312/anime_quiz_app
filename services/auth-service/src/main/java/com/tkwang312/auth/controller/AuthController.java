package com.tkwang312.auth.controller;

import com.tkwang312.auth.dataTransferObject.AuthenticationResponse;
import com.tkwang312.auth.dataTransferObject.LoginRequest;
import com.tkwang312.auth.dataTransferObject.RegisterRequest;
import com.tkwang312.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //get html page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "index";
    }

    // submit signup
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}
