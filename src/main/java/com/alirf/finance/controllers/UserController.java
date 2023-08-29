package com.alirf.finance.controllers;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Rota para gerenciamento de usuários.")
public class UserController {

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    public String get() {
        return "Hello World!!";
    }
}
