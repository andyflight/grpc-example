package com.example.message_service.domain.models;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Message {
  Long fromUserId;
  String fromUserName;
  Integer chatId;
  Long timestamp;
  String content;
}
