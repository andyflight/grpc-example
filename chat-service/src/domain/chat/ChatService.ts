import {Chat} from "@domain/chat/Chat";
import {ChatMember} from "@domain/chat/ChatMember";

export namespace ChatServiceProps {
  export type CreateChatInput = {
    ownerId: number,
    name: string,
  };
  export type CreateChatOutput = Chat;
  
  export type JoinChatInput = { userId: number, chatId: number };
  export type JoinChatOutput = void;
  
  export type LeaveChatInput = { userId: number, chatId: number };
  export type LeaveChatOutput = void;
  
  export type GetMembersToShareMessageInput = { chatId: number, messageFromUserId: number };
  export type GetMembersToShareMessageOutput = ChatMember[];
  
  export type GetMembersInput = { chatId: number };
  export type GetMembersOutput = ChatMember[];
}

export interface IChatService {
  createChat (
    props: ChatServiceProps.CreateChatInput,
  ): Promise<ChatServiceProps.CreateChatOutput>;
  
  joinChat (
    props: ChatServiceProps.JoinChatInput,
  ): Promise<ChatServiceProps.JoinChatOutput>;
  
  leaveChat (
    props: ChatServiceProps.LeaveChatInput,
  ): Promise<ChatServiceProps.LeaveChatOutput>;
  
  getMembers (
    props: ChatServiceProps.GetMembersInput,
  ): Promise<ChatServiceProps.GetMembersOutput>;
  
  getMembersToShareMessage (
    props: ChatServiceProps.GetMembersToShareMessageInput
  ): Promise<ChatServiceProps.GetMembersToShareMessageOutput>;
}