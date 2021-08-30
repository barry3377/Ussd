package com.ussd.app.Ussd.service;

import com.ussd.app.Ussd.entities.User;


public interface UserService {
    User save(UserRegistrationDto userRegistrationDto);
}
