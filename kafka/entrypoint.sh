#!/bin/bash

# Wait for Kafka broker to start
sleep 10

# Create Kafka topic
kafka-topics --bootstrap-server localhost:9092 --topic payment --create --partitions 3 --replication-factor 1
