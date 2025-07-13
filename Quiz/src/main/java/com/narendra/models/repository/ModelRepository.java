package com.narendra.models.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.narendra.models.Quiz;

public interface ModelRepository extends MongoRepository<Quiz, String> {
    // You can add custom methods if needed
}