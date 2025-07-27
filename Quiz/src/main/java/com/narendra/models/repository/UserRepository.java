package com.narendra.models.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.narendra.models.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
