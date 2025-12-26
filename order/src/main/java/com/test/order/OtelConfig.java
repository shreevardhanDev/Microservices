package com.test.order;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OtelConfig {
    // Spring Boot auto-configures the 'OpenTelemetry' bean
    public OtelConfig(OpenTelemetry openTelemetry) {
        OpenTelemetryAppender.install(openTelemetry);
    }
}