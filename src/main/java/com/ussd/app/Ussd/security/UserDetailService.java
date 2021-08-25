package com.ussd.app.Ussd.security;

import com.ussd.app.Ussd.entities.User;
import com.ussd.app.Ussd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw  new UsernameNotFoundException("user not found");
        }
        return new  UserSecurityDetail(user);
    }
}
