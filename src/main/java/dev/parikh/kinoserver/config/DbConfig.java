package dev.parikh.kinoserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("db")
public record DbConfig(String host, String password, int port) {
}
