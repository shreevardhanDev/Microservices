#!/bin/bash

docker network create mynetwork

cd ../compose-files/
docker-compose -f docker-compose-app.yml -p micro-app down

echo 'Starting config server and eureka discovery'
docker-compose -f docker-compose-app.yml -p micro-app up -d