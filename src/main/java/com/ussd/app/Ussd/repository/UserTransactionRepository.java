package com.ussd.app.Ussd.repository;


import com.ussd.app.Ussd.entities.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Long> {
    UserTransaction findByMsisdn(String msisdn);

    Boolean existsByMsisdn(String msisdn);

//    @Modifying
//    @Query(value = "INSERT INTO userTransaction (msisdn) VALUES (?1)")
//    @Transactional
//    UserTransaction addUser(String telephone);

}
