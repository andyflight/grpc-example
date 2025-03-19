package com.example.user_service.domain.repositories;

import com.example.user_service.domain.models.User;

public interface UserRepository {
  
  User save(User user);
  
  User findById(Long id);
  
  void deleteById(Long id);
}