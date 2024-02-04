package dev.parikh.kinoserver.movie;

import dev.parikh.kinoserver.TmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    TmdbClient client;

    @GetMapping("/movie")
    public Movie movie(@RequestParam( value = "id" ) long id) {
        return client.service.getMovie(id);
    }
}
