#!/bin/bash

docker network create mynetwork

cd ../compose-files
docker compose -f docker-compose-infra.yml -p micro-infra down

echo 'Starting infra'
docker compose -f docker-compose-infra.yml -p micro-infra up -d
