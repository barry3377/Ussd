package com.ussd.app.Ussd.service;

import com.ussd.app.Ussd.entities.Role;
import com.ussd.app.Ussd.entities.User;
import com.ussd.app.Ussd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
     User user=new User(userRegistrationDto.getNom(),userRegistrationDto.getNom(),userRegistrationDto.getEmail(),
             userRegistrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));
     return  userRepository.save(user);
    }
}
