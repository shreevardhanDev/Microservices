#!/bin/bash

# 0. Load Images into Kind
echo "Loading images into Kind..."
kind load docker-image gateway:latest
kind load docker-image order:latest
kind load docker-image payment:latest
#kind load docker-image spring-security-jwt:latest
kind load docker-image cloud-config-servier:latest

# 1. Apply Infrastructure (Postgres, Otel, Zipkin, Splunk)
# Path matches your root 'k8s/' folder
echo "Setting up Ghost Services..."
kubectl apply -f infrastructure.yaml

# 2. Apply ALL Services
echo "Pre-registering internal DNS names..."
kubectl apply -f services/

# 3. Start the Config Servier Deployment
# Filename matches your 'config-servier.yaml'
echo "Starting Config Servier..."
kubectl apply -f deployments/config-servier.yaml

# 4. Wait for Config Servier Pod to be Ready
# Assuming metadata name is 'config-servier-deployment' for consistency
echo "Waiting for Config Servier to be ready..."
kubectl rollout status deployment/config-servier-deployment --timeout=60s

# 5. Small buffer for Ultra 9 Speed
echo "Giving Spring Boot 5 seconds to finish initialization..."
sleep 5

# 6. Apply all other Deployments
echo "Launching Microservices..."
kubectl apply -f deployments/

# 7. Apply Ingress Rules
echo "Enabling Ingress..."
kubectl apply -f ingress/

# 8. Monitor Startup progress
echo "All commands sent. Monitoring pods..."
watch -n 1 kubectl get pods
