import {ChatRepository} from '@domain/chat/ChatRepository';
import {ChatServiceProps, IChatService} from "@domain/chat/ChatService";

export class ChatServiceImpl implements IChatService {
  constructor (
    private readonly chatRepository: ChatRepository,
  ) {}
  
  async createChat (
    props: ChatServiceProps.CreateChatInput,
  ): Promise<ChatServiceProps.CreateChatOutput> {
   const chat = await this.chatRepository.create(props.name);
   await this.chatRepository.addMember(chat.id, props.ownerId);
   return chat;
  }
  
  async joinChat (
    props: ChatServiceProps.JoinChatInput,
  ): Promise<ChatServiceProps.JoinChatOutput> {
    await this.chatRepository.addMember(props.chatId, props.userId);
  }
  
  async leaveChat (
    props: ChatServiceProps.LeaveChatInput,
  ): Promise<ChatServiceProps.LeaveChatOutput> {
    await this.chatRepository.deleteMember(props.chatId, props.userId);
    if (!await this.chatRepository.isChatHasMembers(props.chatId)) {
      await this.chatRepository.deleteChat(props.chatId);
    }
  }
  
  getMembers (
    props: ChatServiceProps.GetMembersInput,
  ): Promise<ChatServiceProps.GetMembersOutput> {
    return this.chatRepository.getMembers(props.chatId);
  }
  
  async getMembersToShareMessage (
    props: ChatServiceProps.GetMembersToShareMessageInput,
  ): Promise<ChatServiceProps.GetMembersToShareMessageOutput> {
    const members = await this.chatRepository.getMembers(props.chatId);
    return members.filter((member) => member.userId !== props.messageFromUserId);
  }
}