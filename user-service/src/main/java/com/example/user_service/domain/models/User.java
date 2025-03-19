package com.example.user_service.domain.models;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
  Long id;
  String name;
}