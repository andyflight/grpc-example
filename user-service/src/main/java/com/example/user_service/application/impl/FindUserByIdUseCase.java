package com.example.user_service.application.impl;

import com.example.user_service.application.AbstractUseCase;
import com.example.user_service.domain.models.User;
import com.example.user_service.domain.repositories.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindUserByIdUseCase
  extends AbstractUseCase<FindUserByIdUseCase.Input, FindUserByIdUseCase.Output> {
  
  private final UserRepository userRepository;
  
  @Override
  public Output execute(Input input) {
    validate(input);
    
    User user = userRepository.findById(input.getUserId());
    
    Output output = Output.builder()
      .user(user)
      .build();
    
    postProcess(output);
    
    return output;
  }
  
  @Override
  public void validate(Input input) {
    if (input.getUserId() == null) {
      throw new IllegalArgumentException("User ID cannot be null");
    }
  }
  
  @Override
  public void postProcess(Output output) {
    log.info("User with id {} updated to {}", output.getUser().getId(), output.getUser());
  }
  
  @Value
  @Builder
  public static class Input {
    Long userId;
  }
  
  @Value
  @Builder
  public static class Output {
    User user;
  }
}