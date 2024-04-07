package dev.parikh.kinoserver;

import dev.parikh.kinoserver.config.TmdbConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Service
public class TmdbClient {

    public TmdbService service;

    public TmdbClient(TmdbConfig config) {
        RestClient client = RestClient.builder()
                .baseUrl(config.apiUrl())
                .defaultHeader("Authorization", "Bearer " + config.bearerToken())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        service = factory.createClient(TmdbService.class);
    }
}
