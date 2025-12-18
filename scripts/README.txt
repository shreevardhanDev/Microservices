



creating kafka topics in confluent/cp-kafka docker container
/bin/kafka-topics --bootstrap-server localhost:9092 --topic payment --create --partitions 3 --replication-factor 1


for windows
DOCKER_HOST: tcp://localhost:2375
npm install -g docker-proxy
docker-proxy start