// Original file: ../shared/chat.proto

import type { Long } from '@grpc/proto-loader';

export interface JoinChatRequest {
  'chatId'?: (number);
  'userId'?: (number | string | Long);
}

export interface JoinChatRequest__Output {
  'chatId': (number);
  'userId': (number);
}
