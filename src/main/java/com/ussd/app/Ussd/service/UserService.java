package com.ussd.app.Ussd.service;

import com.ussd.app.Ussd.Dto.UserRegistrationDto;
import com.ussd.app.Ussd.entities.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User save(UserRegistrationDto userRegistrationDto);
}
