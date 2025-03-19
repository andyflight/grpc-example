// Original file: ../shared/chat.proto

import type * as grpc from '@grpc/grpc-js'
import type { MethodDefinition } from '@grpc/proto-loader'
import type { CreateChatRequest as _chat_CreateChatRequest, CreateChatRequest__Output as _chat_CreateChatRequest__Output } from '../chat/CreateChatRequest';
import type { CreateChatResponse as _chat_CreateChatResponse, CreateChatResponse__Output as _chat_CreateChatResponse__Output } from '../chat/CreateChatResponse';
import type { GetMembersRequest as _chat_GetMembersRequest, GetMembersRequest__Output as _chat_GetMembersRequest__Output } from '../chat/GetMembersRequest';
import type { GetMembersResponse as _chat_GetMembersResponse, GetMembersResponse__Output as _chat_GetMembersResponse__Output } from '../chat/GetMembersResponse';
import type { JoinChatRequest as _chat_JoinChatRequest, JoinChatRequest__Output as _chat_JoinChatRequest__Output } from '../chat/JoinChatRequest';
import type { JoinChatResponse as _chat_JoinChatResponse, JoinChatResponse__Output as _chat_JoinChatResponse__Output } from '../chat/JoinChatResponse';
import type { LeaveChatRequest as _chat_LeaveChatRequest, LeaveChatRequest__Output as _chat_LeaveChatRequest__Output } from '../chat/LeaveChatRequest';
import type { LeaveChatResponse as _chat_LeaveChatResponse, LeaveChatResponse__Output as _chat_LeaveChatResponse__Output } from '../chat/LeaveChatResponse';
import type { ShareMessageResponse as _chat_ShareMessageResponse, ShareMessageResponse__Output as _chat_ShareMessageResponse__Output } from '../chat/ShareMessageResponse';
import type { SharedMessageRequest as _chat_SharedMessageRequest, SharedMessageRequest__Output as _chat_SharedMessageRequest__Output } from '../chat/SharedMessageRequest';

export interface ChatServiceClient extends grpc.Client {
  createChat(argument: _chat_CreateChatRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_CreateChatResponse__Output>): grpc.ClientUnaryCall;
  createChat(argument: _chat_CreateChatRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_chat_CreateChatResponse__Output>): grpc.ClientUnaryCall;
  createChat(argument: _chat_CreateChatRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_CreateChatResponse__Output>): grpc.ClientUnaryCall;
  createChat(argument: _chat_CreateChatRequest, callback: grpc.requestCallback<_chat_CreateChatResponse__Output>): grpc.ClientUnaryCall;
  
  getMembers(argument: _chat_GetMembersRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_GetMembersResponse__Output>): grpc.ClientUnaryCall;
  getMembers(argument: _chat_GetMembersRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_chat_GetMembersResponse__Output>): grpc.ClientUnaryCall;
  getMembers(argument: _chat_GetMembersRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_GetMembersResponse__Output>): grpc.ClientUnaryCall;
  getMembers(argument: _chat_GetMembersRequest, callback: grpc.requestCallback<_chat_GetMembersResponse__Output>): grpc.ClientUnaryCall;
  
  joinChat(argument: _chat_JoinChatRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_JoinChatResponse__Output>): grpc.ClientUnaryCall;
  joinChat(argument: _chat_JoinChatRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_chat_JoinChatResponse__Output>): grpc.ClientUnaryCall;
  joinChat(argument: _chat_JoinChatRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_JoinChatResponse__Output>): grpc.ClientUnaryCall;
  joinChat(argument: _chat_JoinChatRequest, callback: grpc.requestCallback<_chat_JoinChatResponse__Output>): grpc.ClientUnaryCall;
  
  leaveChat(argument: _chat_LeaveChatRequest, metadata: grpc.Metadata, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_LeaveChatResponse__Output>): grpc.ClientUnaryCall;
  leaveChat(argument: _chat_LeaveChatRequest, metadata: grpc.Metadata, callback: grpc.requestCallback<_chat_LeaveChatResponse__Output>): grpc.ClientUnaryCall;
  leaveChat(argument: _chat_LeaveChatRequest, options: grpc.CallOptions, callback: grpc.requestCallback<_chat_LeaveChatResponse__Output>): grpc.ClientUnaryCall;
  leaveChat(argument: _chat_LeaveChatRequest, callback: grpc.requestCallback<_chat_LeaveChatResponse__Output>): grpc.ClientUnaryCall;
  
  shareMessage(metadata: grpc.Metadata, options?: grpc.CallOptions): grpc.ClientDuplexStream<_chat_SharedMessageRequest, _chat_ShareMessageResponse__Output>;
  shareMessage(options?: grpc.CallOptions): grpc.ClientDuplexStream<_chat_SharedMessageRequest, _chat_ShareMessageResponse__Output>;
  
}

export interface ChatServiceHandlers extends grpc.UntypedServiceImplementation {
  createChat: grpc.handleUnaryCall<_chat_CreateChatRequest__Output, _chat_CreateChatResponse>;
  
  getMembers: grpc.handleUnaryCall<_chat_GetMembersRequest__Output, _chat_GetMembersResponse>;
  
  joinChat: grpc.handleUnaryCall<_chat_JoinChatRequest__Output, _chat_JoinChatResponse>;
  
  leaveChat: grpc.handleUnaryCall<_chat_LeaveChatRequest__Output, _chat_LeaveChatResponse>;
  
  shareMessage: grpc.handleBidiStreamingCall<_chat_SharedMessageRequest__Output, _chat_ShareMessageResponse>;
  
}

export interface ChatServiceDefinition extends grpc.ServiceDefinition {
  createChat: MethodDefinition<_chat_CreateChatRequest, _chat_CreateChatResponse, _chat_CreateChatRequest__Output, _chat_CreateChatResponse__Output>
  getMembers: MethodDefinition<_chat_GetMembersRequest, _chat_GetMembersResponse, _chat_GetMembersRequest__Output, _chat_GetMembersResponse__Output>
  joinChat: MethodDefinition<_chat_JoinChatRequest, _chat_JoinChatResponse, _chat_JoinChatRequest__Output, _chat_JoinChatResponse__Output>
  leaveChat: MethodDefinition<_chat_LeaveChatRequest, _chat_LeaveChatResponse, _chat_LeaveChatRequest__Output, _chat_LeaveChatResponse__Output>
  shareMessage: MethodDefinition<_chat_SharedMessageRequest, _chat_ShareMessageResponse, _chat_SharedMessageRequest__Output, _chat_ShareMessageResponse__Output>
}
