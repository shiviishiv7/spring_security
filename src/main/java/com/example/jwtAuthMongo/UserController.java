package com.example.jwtAuthMongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);
        return ResponseEntity.ok(save);
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String,String>> getToken(@RequestBody User user){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword()));


        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String generateToken = jwtUtil.generateToken(userDetails);
        Map<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("token",generateToken);
        return ResponseEntity.ok(map);


    }

    @GetMapping("/welcome")
    public String home(){
        return "welcome";
    }



}
