package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction ,Long> {
}
