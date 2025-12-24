#!/bin/bash

docker network create mynetwork

cd ../compose-files
docker-compose -f docker-compose-config.yml -p micro-config down

echo 'Starting config'
docker-compose -f docker-compose-config.yml -p micro-config up -d
