package dev.parikh.kinoserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
public class TmdbImagesClient {

    public RestTemplate restTemplate;

    public TmdbImagesClient(TmdbClient client) {
        String bearerToken = System.getenv("TMDB_BEARER_TOKEN");

        restTemplate = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer " + bearerToken)
                .uriTemplateHandler(new DefaultUriBuilderFactory(client.configuration.images.secureBaseUrl))
                .build();
    }
}
