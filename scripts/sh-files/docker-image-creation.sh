#!/bin/bash

# docker build -t kafka ./
# echo "completed building docker image kafka"

cd ../../config_properties
docker build -t config_properties ./
echo "completed building docker image properties"

cd ../cloud-config-servier
docker build -t cloud-config-servier ./
echo "completed building docker image cloud-config-servier"

#cd ../eureka-server
#docker build -t eureka-server ./
#echo "completed building docker image eureka-server"

cd ../gateway
docker build -t gateway ./
echo "completed building docker image gateway"

cd ../order
docker build -t order ./
echo "completed building docker image order"

cd ../payment
docker build -t payment ./
echo "completed building docker image payment"

#cd ../spring-security-jwt
#docker build -t spring-security-jwt ./
#echo "completed building docker image spring-security-jwt"