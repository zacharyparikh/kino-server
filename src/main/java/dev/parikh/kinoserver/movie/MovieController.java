package dev.parikh.kinoserver.movie;

import dev.parikh.kinoserver.TmdbClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    TmdbClient client;

    public MovieController(TmdbClient client) {
        this.client = client;
    }

    @GetMapping("/movie")
    public Movie movie(@RequestParam( value = "id" ) String id) {
        return client.service.getMovie(id);
    }
}
