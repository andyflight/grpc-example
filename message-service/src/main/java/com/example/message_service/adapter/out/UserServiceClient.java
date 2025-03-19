package com.example.message_service.adapter.out;

import com.example.message_service.domain.models.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import user.GetUserRequest;
import user.GetUserResponse;
import user.UserServiceGrpc;

@Component
@Slf4j
public class UserServiceClient {
  
  private final ManagedChannel channel;
  private final UserServiceGrpc.UserServiceBlockingStub userServiceStub;
  
  public UserServiceClient() {
    this.channel = ManagedChannelBuilder.forAddress("user_service", 9090).usePlaintext().build();
    this.userServiceStub = UserServiceGrpc.newBlockingStub(channel);
  }
  
  public User getUser(Long userId) {
    GetUserRequest request = GetUserRequest.newBuilder().setUserId(userId).build();
    GetUserResponse response;
    try {
      response = userServiceStub.getUser(request);
    } catch (Exception e) {
      return User.builder().name("NotFound").build();
    }
    
    return User.builder().id(response.getUserId()).name(response.getName()).build();
  }
  
}
