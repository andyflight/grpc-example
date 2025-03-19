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
public class CreateUserUseCase
  extends AbstractUseCase<CreateUserUseCase.Input, CreateUserUseCase.Output> {
  
  private final UserRepository userRepository;
  
  @Override
  public Output execute(Input input) {
    validate(input);
    
    User user = User.builder()
      .name(input.getName())
      .build();
    
    Output output = Output.builder()
      .user(userRepository.save(user))
      .build();
    
    postProcess(output);
    
    return output;
  }
  
  @Override
  public void validate(Input input) {
    if (input.getName() == null || input.getName().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
  }
  
  @Override
  public void postProcess(Output output) {
    log.info("User created: {}", output.getUser());
  }
  
  @Value
  @Builder
  public static class Input{
    String name;
  }
  
  @Value
  @Builder
  public static class Output{
    User user;
  }
}

