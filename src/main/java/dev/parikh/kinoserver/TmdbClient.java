package dev.parikh.kinoserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class TmdbClient {

    public final static TmdbService service;
    @Value("${authentication.bearerToken}")
    private static String bearerToken;

    static {
        RestClient client = RestClient.builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .defaultHeader("Authorization", bearerToken)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(TmdbService.class);
    }
}
