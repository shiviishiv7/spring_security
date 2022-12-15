package com.example.AuthenticationTesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsHelperService implements UserDetailsService {

    @Autowired
    private UserRespo userRespo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        System.out.println(username);
        Example<User> example = Example.of(user);
        Optional<User> optionalUser = userRespo.findOne(example);
        if(optionalUser.isPresent()){
            return new UserDetailHelper(optionalUser.get());
        }
        throw new UsernameNotFoundException("User not found "+username);
    }
}
