package com.example.message_service.adapter.out;


import com.example.message_service.domain.repositories.ObserverRepository;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import message.MessageResponse;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class InMemoryObserverRepository implements ObserverRepository {
  private final Map<Long, StreamObserver<MessageResponse>> observers = new ConcurrentHashMap<>();
  
  @Override
  public void save(Long userId, StreamObserver<MessageResponse> observer) {
    observers.put(userId, observer);
  }
  
  @Override
  public StreamObserver<MessageResponse> findByUserId(Long userId) {
    log.info("observers: {}", observers);
    return observers.get(userId);
  }
  
  @Override
  public void deleteByUserId(Long userId) {
    observers.remove(userId);
  }
}
