// Original file: ../shared/chat.proto

import type { Long } from '@grpc/proto-loader';

export interface SharedMessageRequest {
  'fromUserId'?: (number | string | Long);
  'chatId'?: (number);
  'timestamp'?: (number | string | Long);
  'content'?: (string);
}

export interface SharedMessageRequest__Output {
  'fromUserId': (number);
  'chatId': (number);
  'timestamp': (number);
  'content': (string);
}
