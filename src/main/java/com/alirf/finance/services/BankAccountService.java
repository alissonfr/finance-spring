package com.alirf.finance.services;

import com.alirf.finance.models.BankAccount;
import com.alirf.finance.models.User;
import com.alirf.finance.repositories.BankAccountRepository;
import com.alirf.finance.repositories.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public Page<BankAccount> find(String name, Pageable page) {
        return bankAccountRepository.findAll((root, query, builder) -> builder.and(filter(root, builder, name)), page);
    }

    private Predicate[] filter(Root root, CriteriaBuilder builder, String name) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) predicates.add(builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

        return predicates.toArray(Predicate[]::new);
    }

    public BankAccount insert(BankAccount user) {
        return bankAccountRepository.save(user);
    }

    public BankAccount getById(Integer id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada:  " + id));
    }

    public BankAccount update(Integer id, BankAccount user) {
        BankAccount bankAccountExists = getById(id);

        return this.bankAccountRepository.save(user);
    }


}
