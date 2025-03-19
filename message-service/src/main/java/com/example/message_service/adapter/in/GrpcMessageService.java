package com.example.message_service.adapter.in;

import com.example.message_service.application.impl.SaveObserverUseCase;
import com.example.message_service.application.impl.SendMessageUseCase;
import com.example.message_service.domain.models.Message;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import message.MessageRequest;
import message.MessageResponse;
import message.MessageServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class GrpcMessageService extends MessageServiceGrpc.MessageServiceImplBase {
  
  private final SendMessageUseCase sendMessageUseCase;
  private final SaveObserverUseCase saveObserverUseCase;
  
  @Override
  public StreamObserver<MessageRequest> messageStream(StreamObserver<MessageResponse> responseObserver) {
    log.info("service executed");
    
    return new StreamObserver<MessageRequest>() {
      @Override
      public void onNext(MessageRequest request) {
        log.info("Received message request: {}", request);
        
        if (request.hasPartialRequest()){
          saveObserverUseCase.execute(
            SaveObserverUseCase.Input.builder().userId(request.getPartialRequest().getFromUserId()).observer(responseObserver).build()
          );
        }
        else {
          sendMessageUseCase.execute(
            SendMessageUseCase.Input.builder()
              .message(Message.builder()
                .chatId(request.getFullRequest().getChatId())
                .fromUserId(request.getFullRequest().getFromUserId())
                .fromUserName("fromUserName")
                .timestamp(123L)
                .content(request.getFullRequest().getContent())
                .build())
              .build()
          );
        }
        
      }
      
      @Override
      public void onError(Throwable t) {
        log.error("Error in message stream: {}", t.getMessage());
      }
      
      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
  }
  

}
