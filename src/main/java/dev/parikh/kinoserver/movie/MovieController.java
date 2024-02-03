package dev.parikh.kinoserver.movie;

import dev.parikh.kinoserver.TmdbClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movie")
    public Movie movie(@RequestParam( value = "id" ) long id) {
        return TmdbClient.service.getMovie(id);
    }
}
