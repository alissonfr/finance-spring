package com.alirf.finance.controllers;

import com.alirf.finance.models.BankAccount;
import com.alirf.finance.services.BankAccountService;
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
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(required = false) String name,
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 10) Pageable page
    ) {
        Page<BankAccount> bankAccounts = this.bankAccountService.find(name, page);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Integer id
    ) {
        BankAccount bankAccount = this.bankAccountService.getById(id);
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody BankAccount bankAccount
    ) {
        BankAccount createdBankAccount = this.bankAccountService.insert(bankAccount);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @RequestBody BankAccount bankAccount
    ) {
        BankAccount updatedBankAccount = this.bankAccountService.update(id, bankAccount);
        return new ResponseEntity<>(updatedBankAccount, HttpStatus.OK);
    }
}
