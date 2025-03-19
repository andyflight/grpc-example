// Original file: ../shared/chat.proto

import type { Long } from '@grpc/proto-loader';

export interface CreateChatRequest {
  'ownerId'?: (number | string | Long);
  'name'?: (string);
}

export interface CreateChatRequest__Output {
  'ownerId': (number);
  'name': (string);
}
