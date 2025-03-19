package com.example.user_service.adapter.in.controller;

import com.example.user_service.application.impl.CreateUserUseCase;
import com.example.user_service.application.impl.DeleteUserUseCase;
import com.example.user_service.application.impl.FindUserByIdUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import user.*;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class GrpcUserService extends UserServiceGrpc.UserServiceImplBase {
  
  private final CreateUserUseCase createUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;
  private final FindUserByIdUseCase findUserByIdUseCase;
  
  @Override
  public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
    
    log.info("createUser received request: {}", request);
    
    CreateUserUseCase.Output output = createUserUseCase.execute(CreateUserUseCase.Input.builder().name(request.getName()).build());
    
    CreateUserResponse response = CreateUserResponse.newBuilder()
      .setUserId(output.getUser().getId())
      .setName(output.getUser().getName())
      .build();
    
    responseObserver.onNext(response);

    responseObserver.onCompleted();
  }
  
  @Override
  public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
    
    log.info("getUser received request: {}", request);
    
    FindUserByIdUseCase.Output output = findUserByIdUseCase.execute(FindUserByIdUseCase.Input.builder().userId(request.getUserId()).build());
    
    GetUserResponse response = GetUserResponse.newBuilder()
      .setUserId(output.getUser().getId())
      .setName(output.getUser().getName())
      .build();
    
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
  
  @Override
  public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserResponse> responseObserver) {
    log.info("deleteUser received request: {}", request);
    
    DeleteUserUseCase.Output output = deleteUserUseCase.execute(DeleteUserUseCase.Input.builder().id(request.getUserId()).build());
    
    DeleteUserResponse response = DeleteUserResponse.newBuilder()
      .setStatus(output.getStatus())
      .build();
    
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}
