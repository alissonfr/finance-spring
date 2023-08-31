package com.alirf.finance.controllers;

import com.alirf.finance.models.User;
import com.alirf.finance.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Rota para gerenciamento de usuários.")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Buscar usuários", description= "Busca usuários cadastrados no sistema")
    public ResponseEntity<?> find(
            @Parameter(description = "Nome do usuário") @RequestParam(required = false) String name,
            @Parameter(description = "Valores para paginação") @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable page
    ) {
        Page<User> users = this.userService.find(name, page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Buscar usuário", description= "Busca um usuário cadastrado no sistema")
    public ResponseEntity<?> getById(
            @Parameter(description = "ID do usuário") @PathVariable Integer id
    ) {
        User user = this.userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Criar usuário", description= "Cria um novo usuário")
    public ResponseEntity<?> create(
            @Parameter(description = "Dados do usuário a ser criado") @RequestBody User user
    ) {
        User createdUser = this.userService.insert(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Atualizar usuário", description= "Altera os dados de um usuário")
    public ResponseEntity<?> update(
            @Parameter(description = "ID do usuário") @PathVariable Integer id,
            @Parameter(description = "Dados do usuário a ser atualizado") @RequestBody User user
    ) {
        User updatedUser = this.userService.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
