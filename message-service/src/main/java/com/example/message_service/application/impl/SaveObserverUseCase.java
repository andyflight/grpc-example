package com.example.message_service.application.impl;

import com.example.message_service.application.AbstractUseCase;
import com.example.message_service.domain.repositories.ObserverRepository;
import io.grpc.stub.StreamObserver;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import message.MessageResponse;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveObserverUseCase extends AbstractUseCase<SaveObserverUseCase.Input, SaveObserverUseCase.Output> {
  
  private final ObserverRepository observerRepository;
  
  @Override
  public Output execute(Input input) {
    
    log.info("got input {}", input.getObserver());
    observerRepository.save(input.userId, input.getObserver());
    return Output.builder().build();
  }
  
  @Value
  @Builder
  public static class Input {
    Long userId;
    StreamObserver<MessageResponse> observer;
  }
  
  
  @Value
  @Builder
  public static class Output {
  }
}
