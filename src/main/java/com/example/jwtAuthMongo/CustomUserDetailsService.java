package com.example.jwtAuthMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        Example<User> example = Example.of(user);
        Optional<User> optionalUser = userRepo.findOne(example);
        if(optionalUser.isPresent()){
            return new CustomUserDetail(optionalUser.get());
        }
        throw new UsernameNotFoundException("User not found "+username);
    }
}
