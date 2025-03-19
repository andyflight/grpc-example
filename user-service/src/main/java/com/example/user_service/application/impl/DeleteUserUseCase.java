package com.example.user_service.application.impl;

import com.example.user_service.application.AbstractUseCase;
import com.example.user_service.domain.repositories.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteUserUseCase
  extends AbstractUseCase<DeleteUserUseCase.Input, DeleteUserUseCase.Output> {
  
  private final UserRepository userRepository;
  
  @Override
  public Output execute(Input input) {
    validate(input);
    
    userRepository.deleteById(input.getId());
    Output output = Output.builder()
      .status("Deleted successfully")
      .build();
    
    postProcess(output);
    
    return output;
  }
  
  @Override
  public void validate(Input input) {
    if (input.getId() == null) {
      throw new IllegalArgumentException("User ID cannot be null");
    }
  }
  
  @Override
  public void postProcess(Output output) {
    log.info("User deletion: {}", output.getStatus());
  }
  
  @Value
  @Builder
  public static class Input{
    Long id;
  }
  
  @Value
  @Builder
  public static class Output{
    String status;
  }
}
