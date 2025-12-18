Install gradle 8.8 
java 22
change data source url to local ipconfig url on COMMON-DATA-SOURCE.yml
use sh build.sh etc in the scripts folder.
in order service we have initial database scripts to run in database

add 
host    all             all             0.0.0.0/0               md5
in pg_hba.conf in the postgressql data folder to make it work. and restart system

user name and password for jwt creation is:
username: shree
password: shree



Note:
sometimes #EUREKA_CLIENT_PROP.yml
eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://192.168.3.104:8761/eureka
  instance:
    hostname: 192.168.3.104

here hostname and defaultZone might be changed to direct ipconfig address like '192.168.3.104' or 'eureka-server'
try both. one will work.



request format:

curl --location 'http://localhost:8083/jwtauth/authenticate' `
--header 'Content-Type: application/json' `
--data '{
   "username": "shree",
   "password": "shree"
}'

curl --location 'http://localhost:8083/order/createOrder' `
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJlZSIsImV4cCI6MTcyNDc3Njg3MiwiaWF0IjoxNzI0Nzc1MDcyfQ.FMF87tikrY2okJrmoCR2ijgp-y8LZe3xelAMZPhx_Hw' `
--header 'Content-Type: application/json' `
--data '{
    "order": {
        "description": "working"
    },
    "payment": {
        "transactionId": 4,
        "amount": 2321.00,
        "description": "testing"
    }
}'

curl --location 'http://localhost:8083/payment/createPayment' `
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJlZSIsImV4cCI6MTcyNDc3Njg3MiwiaWF0IjoxNzI0Nzc1MDcyfQ.FMF87tikrY2okJrmoCR2ijgp-y8LZe3xelAMZPhx_Hw' `
--header 'Content-Type: application/json' `
--data '{
    "payment": {
        "transactionId": 4,
        "amount": 2321.00,
        "description": "testing"
    }
}'