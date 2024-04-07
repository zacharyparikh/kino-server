package dev.parikh.kinoserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tmdb")
public record TmdbConfig(String apiUrl, String bearerToken) {
}
