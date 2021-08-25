package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u  From User u WHERE u.email=?1")
 User findByEmail(String email);
}
