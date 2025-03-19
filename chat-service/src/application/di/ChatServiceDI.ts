import {ChatServiceImpl} from "@application/services/ChatServiceImpl";
import {ChatRepositoryDI} from "@application/di/ChatRepositoryDI";

export const ChatServiceDI = new ChatServiceImpl(ChatRepositoryDI);