package com.example.message_service.adapter.out;

import chat.Chat;
import chat.ChatServiceGrpc;
import com.example.message_service.domain.models.Message;
import com.example.message_service.domain.repositories.ObserverRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import message.MessageResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChatServiceClient {
  
  private final ManagedChannel channel;
  private final ChatServiceGrpc.ChatServiceStub chatServiceStub;
  private final ObserverRepository observerRepository;
  private final UserServiceClient userServiceClient;
  private StreamObserver<Chat.SharedMessageRequest> requestObserver;
  
  public ChatServiceClient(ObserverRepository observerRepository, UserServiceClient userServiceClient) {
    // Ініціалізація каналу до ChatService на localhost:9091
    this.channel = ManagedChannelBuilder.forAddress("chat_service", 9091)
      .usePlaintext() // Використовуємо plaintext для простоти (без TLS)
      .build();
    this.chatServiceStub = ChatServiceGrpc.newStub(channel);
    this.observerRepository = observerRepository;
    this.userServiceClient = userServiceClient;
  }
  
  @PostConstruct
  public void init() {
    
    log.info("Chat service client started");
    StreamObserver<Chat.ShareMessageResponse> responseObserver = new StreamObserver<Chat.ShareMessageResponse>() {
      
      @Override
      public void onNext(Chat.ShareMessageResponse response) {
        Long toUserId = response.getToUserId();
        log.info("message for user {}", toUserId);
        StreamObserver<MessageResponse> userObserver = observerRepository.findByUserId(toUserId);
        
        log.info("observer found {}", userObserver);
        
        userObserver.onNext(MessageResponse.newBuilder()
          .setFromUserId(response.getMessage().getFromUserId())
          .setFromUserName(userServiceClient.getUser(response.getMessage().getFromUserId()).getName())
          .setChatId(response.getMessage().getChatId())
          .setContent(response.getMessage().getContent())
          .setTimestamp(response.getMessage().getTimestamp())
          .build());
        log.info("user received message on {}", userObserver);
      }
      
      @Override
      public void onError(Throwable t) {
      
      }
      
      @Override
      public void onCompleted() {
      }
    };
    
    requestObserver = chatServiceStub.shareMessage(responseObserver);
    log.info("request observer created");
  }
  
  
  public void shareMessage(Message message) {
    init();
    log.info("Sending share message");
    Chat.SharedMessageRequest request = Chat.SharedMessageRequest.newBuilder()
      .setFromUserId(message.getFromUserId())
      .setChatId(message.getChatId())
      .setTimestamp(message.getTimestamp())
      .setContent(message.getContent())
      .build();
    
    log.info("request to chat {}", request);
      
    requestObserver.onNext(request);
    
    log.info("message sent");
  }
}