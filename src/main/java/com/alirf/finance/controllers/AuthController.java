package com.alirf.finance.controllers;

import com.alirf.finance.dtos.request.AuthRequestDTO;
import com.alirf.finance.dtos.request.RegisterRequestDTO;
import com.alirf.finance.dtos.response.AuthResponseDTO;
import com.alirf.finance.models.User;
import com.alirf.finance.services.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Rota para autenticação de usuários.")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    public ResponseEntity<?> register(
            @Parameter(description = "Dados para o registro") @RequestBody RegisterRequestDTO auth
    ) {
        AuthResponseDTO authResponse = this.authService.register(auth);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    public ResponseEntity<?> login(
            @Parameter(description = "Dados para a autenticação") @RequestBody AuthRequestDTO auth
    ) {
        AuthResponseDTO authResponse = this.authService.login(auth);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
