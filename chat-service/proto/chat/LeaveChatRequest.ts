// Original file: ../shared/chat.proto

import type { Long } from '@grpc/proto-loader';

export interface LeaveChatRequest {
  'chatId'?: (number);
  'userId'?: (number | string | Long);
}

export interface LeaveChatRequest__Output {
  'chatId': (number);
  'userId': (number);
}
