package com.alirf.finance.controllers;

import com.alirf.finance.models.BankAccount;
import com.alirf.finance.models.User;
import com.alirf.finance.services.BankAccountService;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("bank-accounts")
@RequiredArgsConstructor
@Tag(name = "Contas bancárias", description = "Rota para gerenciamento de contas bancárias.")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Buscar contas bancárias", description= "Busca contas bancárias cadastradas no sistema")
    public ResponseEntity<?> find(
            @Parameter(description = "Nome da conta bancária") @RequestParam(required = false) String name,
            @Parameter(description = "Valores para paginação") @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable page
    ) {
        Page<BankAccount> bankAccounts = this.bankAccountService.find(name, page);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Buscar conta bancária", description= "Busca uma conta bancária cadastrada no sistema")
    public ResponseEntity<?> getById(
            @Parameter(description = "ID da conta bancária") @PathVariable Integer id
    ) {
        BankAccount bankAccount = this.bankAccountService.getById(id);
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta bancária cadastrada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Criar conta bancária", description= "Cria uma nova conta bancária")
    public ResponseEntity<?> create(
            @Parameter(description = "Dados da conta bancária a ser criada") @RequestBody BankAccount bankAccount
    ) {
        BankAccount createdBankAccount = this.bankAccountService.insert(bankAccount);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta bancária atualizada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Usuário não autorizado."),
            @ApiResponse(responseCode = "500", description = "Erro de requisição.")
    })
    @Operation(summary = "Atualizar conta bancária", description= "Altera os dados de uma conta bancária")
    public ResponseEntity<?> update(
            @Parameter(description = "ID da conta bancária") @PathVariable Integer id,
            @Parameter(description = "Dados da conta bancária a ser atualizada") @RequestBody BankAccount bankAccount
    ) {
        BankAccount updatedBankAccount = this.bankAccountService.update(id, bankAccount);
        return new ResponseEntity<>(updatedBankAccount, HttpStatus.OK);
    }
}
