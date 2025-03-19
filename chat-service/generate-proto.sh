#!/bin/bash

rm -rf ./proto
mkdir -p ./proto

PROTO_FILES="${1:-*.proto}"

proto-loader-gen-types \
    --longs=Number \
    --enums=String \
    --defaults \
    --oneofs \
    --grpcLib=@grpc/grpc-js \
    --outDir=./proto/ \
    ../shared/${PROTO_FILES}