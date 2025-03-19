package com.example.user_service.adapter.out;

import com.example.user_service.domain.models.User;
import com.example.user_service.domain.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {
  
  private final AtomicLong counter = new AtomicLong(1);
  private final ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
  
  @Override
  public User save(User user) {
    User newUser = user.toBuilder()
      .id(counter.getAndIncrement())
      .build();
    
    users.put(newUser.getId(), newUser);
    return newUser;
  }
  
  @Override
  public User findById(Long id) {
    User foundUser = users.get(id);
    if (foundUser == null) {
      throw new IllegalArgumentException("User not found");
    }
    return foundUser;
  }
  
  @Override
  public void deleteById(Long id) {
    users.remove(id);
  }
  
}