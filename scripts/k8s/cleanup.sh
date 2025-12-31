#!/bin/bash

echo "Cleaning up Microservices and Ingress..."

# Deletes all app pods, services, and ingress rules
kubectl delete -f deployments/
kubectl delete -f services/
kubectl delete -f ingress/

echo "Clean-up complete. Infrastructure (Postgres/Otel) is still active."
kubectl get pods
