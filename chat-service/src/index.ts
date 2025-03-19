import * as grpc from '@grpc/grpc-js';
import * as protoLoader from '@grpc/proto-loader';
import * as grpcReflection from '@grpc/reflection';
import * as path from "node:path";
import {ProtoGrpcType} from "@proto/chat";
import {GrpcChatService} from "@application/grpc/GrpcChatService";
import {ChatServiceDI} from "@application/di/ChatServiceDI";

const PROTO_PATH = path.join(process.cwd(), '../shared/chat.proto');
const server = new grpc.Server();

const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  enums: String,
  longs: Number,
  defaults: true,
  oneofs: true,
});

const reflection = new grpcReflection.ReflectionService(packageDefinition);
const userProto = grpc.loadPackageDefinition(packageDefinition) as unknown as ProtoGrpcType;

reflection.addToServer(server);
server.addService(userProto.chat.ChatService.service, new GrpcChatService(ChatServiceDI).handlers())

server.bindAsync(
  '0.0.0.0:9091',
  grpc.ServerCredentials.createInsecure(),
  (error, port) => {
    console.log(`Started on localhost:${port}`);
  }
);