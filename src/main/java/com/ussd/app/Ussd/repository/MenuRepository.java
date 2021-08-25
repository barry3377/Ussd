package com.ussd.app.Ussd.repository;

import com.ussd.app.Ussd.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository  extends JpaRepository<Menu,Long> {
    Menu findByLevel(String level);
}
