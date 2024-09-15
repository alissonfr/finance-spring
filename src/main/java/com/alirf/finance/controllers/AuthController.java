package com.alirf.finance.controllers;

import com.alirf.finance.dtos.request.AuthRequestDTO;
import com.alirf.finance.dtos.request.RegisterRequestDTO;
import com.alirf.finance.dtos.response.AuthResponseDTO;
import com.alirf.finance.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequestDTO auth
    ) {
        AuthResponseDTO authResponse = this.authService.register(auth);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequestDTO auth
    ) {
        AuthResponseDTO authResponse = this.authService.login(auth);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
