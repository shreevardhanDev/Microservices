FROM confluentinc/cp-kafka:6.0.0

WORKDIR /app
COPY entrypoint.sh /app/entrypoint.sh
USER root
RUN chmod +x /app/entrypoint.sh
CMD "/etc/confluent/docker/run"
ENTRYPOINT ["/bin/bash","/app/entrypoint.sh"]