#!/usr/bin/env bash

echo "Maven build"
./mvnw clean package

echo "Building image"
docker build --tag events:latest .

docker-compose up