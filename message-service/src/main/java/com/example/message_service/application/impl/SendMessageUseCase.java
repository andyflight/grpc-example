package com.example.message_service.application.impl;

import com.example.message_service.adapter.out.ChatServiceClient;
import com.example.message_service.application.AbstractUseCase;
import com.example.message_service.domain.models.Message;
import com.example.message_service.domain.repositories.ObserverRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendMessageUseCase extends AbstractUseCase<SendMessageUseCase.Input, SendMessageUseCase.Output> {
  
  private final ObserverRepository observerRepository;
  private final ChatServiceClient chatServiceClient;
  
  @Override
  public Output execute(Input input) {
    
    log.info("service executed");
    chatServiceClient.shareMessage(input.message);
    return Output.builder().build();
  }
  
  
  @Value
  @Builder
  public static class Input {
      Message message;
  }
  
  @Value
  @Builder
  public static class Output {
  
  }
}
