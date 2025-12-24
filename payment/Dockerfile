# syntax=docker/dockerfile:1

FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

COPY build/libs/ target

# Get the JAR file dynamically using 'find' command and set it as an environment variable
RUN export APP_JAR=$(find target -name "*.jar" | grep -v plain) && \
    echo "Application JAR: $APP_JAR" && \
    echo "#!/bin/sh\njava -jar $APP_JAR \$@" > /app/entrypoint.sh && \
    chmod +x /app/entrypoint.sh

# Use the entrypoint script as the command
ENTRYPOINT ["/app/entrypoint.sh"]