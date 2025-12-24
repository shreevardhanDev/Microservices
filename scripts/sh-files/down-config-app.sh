#!/bin/bash

cd ../compose-files/
docker compose -f docker-compose-app.yml -p micro-app down
docker compose -f docker-compose-config.yml -p micro-config down


