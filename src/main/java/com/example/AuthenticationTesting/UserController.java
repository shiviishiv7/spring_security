package com.example.AuthenticationTesting;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRespo userRespo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public User getRegister(@RequestBody User user){
//        User user = new User("uu","sss","0000");
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        User save = userRespo.save(user);
        return save;
    }

    @PostMapping("/token")
    public String getToken(@RequestBody User user) throws Exception {

            try{

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

            }catch (UsernameNotFoundException e){
                    throw new Exception(e.getLocalizedMessage());
            }catch (BadCredentialsException e){
                    throw new Exception(e.getLocalizedMessage());
            }
            return "AuthDone";

    }




}
