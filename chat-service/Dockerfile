FROM node:17-alpine as build

WORKDIR /home/app/service
COPY ./chat-service/package.json .
COPY ./chat-service/package-lock.json .

RUN npm ci
COPY ./shared/ /home/app/shared
COPY ./chat-service/ .

RUN npm run proto:gen && npm run build

FROM node:17-alpine

WORKDIR /home/app/service
COPY ./chat-service/package.json .
COPY ./chat-service/package-lock.json .
RUN npm ci --omit=dev
COPY --from=build /home/app/service/dist/ ./dist
COPY ./shared/ ../shared

EXPOSE 9091

ENTRYPOINT ["npm", "start"]