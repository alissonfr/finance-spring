package com.alirf.finance.repositories;

import com.alirf.finance.models.BankAccount;
import com.alirf.finance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>, JpaSpecificationExecutor {
}
