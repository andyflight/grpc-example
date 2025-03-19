import {Chat} from "./Chat";
import {ChatMember} from "@domain/chat/ChatMember";

export interface ChatRepository {
  create (name: string): Promise<Chat>;
  addMember (chatId: number, userId: number): Promise<ChatMember>;
  deleteMember (chatId: number, userId: number): Promise<void>;
  deleteChat (chatId: number): Promise<void>;
  getMembers (chatId: number): Promise<ChatMember[]>;
  isChatHasMembers (chatId: number): Promise<boolean>;
}