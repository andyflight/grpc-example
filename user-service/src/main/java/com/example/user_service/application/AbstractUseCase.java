package com.example.user_service.application;


public abstract class AbstractUseCase<Input, Output> {
  
  public abstract Output execute(Input input);
  
  public void validate(Input input) {
  }
  
  public void postProcess(Output output) {
  }
}