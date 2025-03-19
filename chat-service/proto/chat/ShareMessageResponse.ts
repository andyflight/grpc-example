// Original file: ../shared/chat.proto

import type { SharedMessageRequest as _chat_SharedMessageRequest, SharedMessageRequest__Output as _chat_SharedMessageRequest__Output } from '../chat/SharedMessageRequest';

export interface ShareMessageResponse {
  'toUserId'?: (number);
  'message'?: (_chat_SharedMessageRequest | null);
}

export interface ShareMessageResponse__Output {
  'toUserId': (number);
  'message': (_chat_SharedMessageRequest__Output | null);
}
