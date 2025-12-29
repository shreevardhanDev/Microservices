#!/bin/bash

cd ../../cloud-config-servier
gradle build -x test
echo "completed building cloud-config-servier"

#cd ../eureka-server
#gradle build -x test
#echo "completed building eureka-server"

cd ../gateway
gradle build -x test
echo "completed building gateway"

cd ../order
gradle build -x test
echo "completed building order"

cd ../payment
gradle build -x test
echo "completed building payment"

#cd ../spring-security-jwt
#gradle build -x test
#echo "completed building spring-security-jwt"