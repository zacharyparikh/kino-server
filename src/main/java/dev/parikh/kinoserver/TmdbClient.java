package dev.parikh.kinoserver;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Service
public class TmdbClient {

    public TmdbService service;

    public TmdbClient(Environment environment) {
        String bearerToken = environment.getProperty("authorization.bearerToken");
        RestClient client = RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .defaultHeader("Authorization", "Bearer " + bearerToken)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        service = factory.createClient(TmdbService.class);
    }
}
