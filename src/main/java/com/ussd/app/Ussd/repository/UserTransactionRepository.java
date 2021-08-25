package com.ussd.app.Ussd.repository;


import com.ussd.app.Ussd.entities.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {
    UserTransaction findByMsisdn(String msisdn);

    Boolean existsByMsisdn(String msisdn);

}
