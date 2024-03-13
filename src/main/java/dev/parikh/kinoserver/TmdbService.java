package dev.parikh.kinoserver;


import dev.parikh.kinoserver.movie.Movie;
import dev.parikh.kinoserver.search.Search;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface TmdbService {

    @GetExchange("/movie/{movieId}")
    Movie getMovie(@PathVariable String movieId);

    @GetExchange("/search/multi?language=en-US&page=1&region=US")
    Search getSearch(@RequestParam String query);

    @GetExchange("/search/movie?language=en-US&region=US")
    Search getSearchMovies(@RequestParam String query, @RequestParam String page);

    @GetExchange("/search/person?language=en-US&page=1")
    Search getSearchPeople(@RequestParam String query);

}
