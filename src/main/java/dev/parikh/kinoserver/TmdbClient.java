package dev.parikh.kinoserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.ToString;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.List;

@Service
public class TmdbClient {

    public TmdbService service;
    public Configuration configuration;
    public RestTemplate restTemplate;

    public TmdbClient() {
        String bearerToken = System.getenv("TMDB_BEARER_TOKEN");

        restTemplate = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Bearer " + bearerToken)
                .uriTemplateHandler(new DefaultUriBuilderFactory("https://api.themoviedb.org/3/"))
                .additionalMessageConverters(
                        new MappingJackson2HttpMessageConverter(
                                new Jackson2ObjectMapperBuilder()
                                        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                                        .build()
                        )
                )
                .build();

        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        service = factory.createClient(TmdbService.class);
        configuration = restTemplate.getForObject("/configuration", Configuration.class);
    }

    @ToString
    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Configuration {

        public Images images;

        @ToString
        @JsonIgnoreProperties(ignoreUnknown=true)
        public static class Images {
            public String secureBaseUrl;
            public List<String> backdropSizes;
        }
    }
}
