import {ChatRepository} from "@domain/chat/ChatRepository";
import {ChatMember} from "@domain/chat/ChatMember";
import {Chat} from "@domain/chat/Chat";

export class ChatRepositoryImpl implements ChatRepository {
  private lastChatId = 1;
  
  constructor(
    private chatStorage = new Map<number, Chat>(),
    private chatMemberStorage = new Array<ChatMember>(),
  ) {}
  
  create (name: string): Promise<Chat> {
    const chat = new Chat(this.lastChatId, name);
    this.chatStorage.set(this.lastChatId++, chat);
    return Promise.resolve(chat);
  }
  
  addMember(chatId: number, userId: number): Promise<ChatMember> {
    const chatMember = new ChatMember(userId, chatId);
    this.chatMemberStorage.push(chatMember);
    return Promise.resolve(chatMember);
  }
  
  async deleteMember (chatId: number, userId: number): Promise<void> {
    this.chatMemberStorage = this.chatMemberStorage.filter(
      (chatMember) => chatMember.userId !== userId ||
                      chatMember.chatId !== chatId,
    );
  }
  
  async deleteChat(chatId: number): Promise<void> {
    this.chatStorage.delete(chatId);
  }
  
  getMembers(chatId: number): Promise<ChatMember[]> {
    const members = this.chatMemberStorage.filter((member) => member.chatId === chatId);
    return JSON.parse(JSON.stringify((members)));
  }
  
  isChatHasMembers(chatId: number): Promise<boolean> {
    return Promise.resolve(this.chatMemberStorage.length > 0);
  }
}