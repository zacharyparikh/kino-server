package dev.parikh.kinoserver.watchlist;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.set.ListOrderedSet;

public class Watchlist {

    private final ObjectMapper mapper = new ObjectMapper();

    @JsonValue
    private final ListOrderedSet<String> watchlist;

    Watchlist(String watchlistString) throws JsonProcessingException {

        if (watchlistString == null) {
            this.watchlist = new ListOrderedSet<>();
            return;
        }

        this.watchlist = mapper.readValue(watchlistString, new TypeReference<>() {
        });
    }

    public void add(String movieId, Integer index) {

        if (index != null) {
            watchlist.add(index, movieId);
        } else {
            watchlist.add(movieId);
        }
    }

    public void remove(String movieId) {
        watchlist.remove(movieId);
    }

    public void reorder(String movieId, int index) {

        if (!watchlist.remove(movieId)) {
            return;
        }

        watchlist.add(index, movieId);
    }

    @Override
    public String toString() {
        try {
            return mapper.writeValueAsString(watchlist);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
