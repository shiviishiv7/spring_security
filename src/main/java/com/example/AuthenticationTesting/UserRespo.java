package com.example.AuthenticationTesting;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRespo extends MongoRepository<User,String> {
}
