// Original file: ../shared/chat.proto

import type { Long } from '@grpc/proto-loader';

export interface ChatMember {
  'userId'?: (number | string | Long);
  'chatId'?: (number);
}

export interface ChatMember__Output {
  'userId': (number);
  'chatId': (number);
}
