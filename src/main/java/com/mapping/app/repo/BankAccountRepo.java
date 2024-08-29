package com.mapping.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapping.app.model.BankAccount;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long>{

}
