package dev.parikh.kinoserver.search;

import dev.parikh.kinoserver.TmdbClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/search")
    Search search(@RequestParam(value = "query") String query) {
        return TmdbClient.service.getSearch(query);
    }

    @GetMapping("/search/movies")
    Search searchMovies(@RequestParam(value = "query") String query) {
        return TmdbClient.service.getSearchMovies(query);
    }

    @GetMapping("/search/people")
    Search searchPeople(@RequestParam(value = "query") String query) {
        return TmdbClient.service.getSearchPeople(query);
    }
}
