package dev.parikh.kinoserver.search;

import dev.parikh.kinoserver.TmdbClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    TmdbClient client;

    public SearchController(TmdbClient client) {
        this.client = client;
    }

    @GetMapping("/search")
    Search search(@RequestParam(value = "query") String query) {
        return client.service.getSearch(query);
    }

    @GetMapping("/search/movies")
    Search searchMovies(@RequestParam(value = "query") String query) {
        return client.service.getSearchMovies(query);
    }

    @GetMapping("/search/people")
    Search searchPeople(@RequestParam(value = "query") String query) {
        return client.service.getSearchPeople(query);
    }
}
