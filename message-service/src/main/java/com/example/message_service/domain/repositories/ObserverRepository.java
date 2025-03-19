package com.example.message_service.domain.repositories;

import io.grpc.stub.StreamObserver;
import message.MessageResponse;

public interface ObserverRepository {
  void save(Long userId, StreamObserver<MessageResponse> observer);
  StreamObserver<MessageResponse> findByUserId(Long userId);
  void deleteByUserId(Long userId);
}

