import {IChatService} from "@domain/chat/ChatService";
import {ChatServiceHandlers} from "@proto/chat/ChatService";
import {ShareMessageResponse} from "@proto/chat/ShareMessageResponse";

export class GrpcChatService {
  constructor (
    private readonly chatService: IChatService,
  ) {}
  
  handlers (): ChatServiceHandlers {
    const { chatService } = this;
    return {
      async createChat (call, callback) {
        const chat = await chatService.createChat({
          ownerId: call.request.ownerId,
          name: call.request.name,
        });
        
        callback(null, {
          id: chat.id,
          name: chat.name,
        });
      },
      
      async joinChat (call, callback) {
        console.log(call.request);
        await chatService.joinChat({
          chatId: call.request.chatId,
          userId: call.request.userId,
        });
        
        callback(null, {
          status: 'ok',
        })
      },
      
      async leaveChat (call, callback) {
        await chatService.leaveChat({
          userId: call.request.userId,
          chatId: call.request.chatId,
        });
        
        callback(null, {
          status: 'ok',
        })
      },
      
      async getMembers (call, callback) {
        const members = await chatService.getMembers({
          chatId: call.request.chatId,
        });
        
        callback(null, {
          members: members.map((member) => ({
            chatId: member.chatId,
            userId: member.userId,
          })),
        });
      },
      
      async shareMessage (call) {
        call.on('data', async (message) => {
          const members = await chatService.getMembersToShareMessage({
            chatId: message.chatId,
            messageFromUserId: message.fromUserId,
          });
          
          console.log('Share message:', members);
          
          members.forEach((member) => {
            call.write({
              toUserId: member.userId,
              message,
            } as ShareMessageResponse)
          });
        });
      }
    };
  }
}