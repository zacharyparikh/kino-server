package dev.parikh.kinoserver.watchlist;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.parikh.kinoserver.config.DbConfig;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

    private final String watchlistKey = "watchlist";
    private final JedisPool pool;

    public WatchlistController(DbConfig config) {
        var clientConfig = DefaultJedisClientConfig.builder().password(config.password()).build();
        this.pool = new JedisPool(new HostAndPort(config.host(), config.port()), clientConfig);
    }

    @GetMapping
    public Watchlist getWatchlist() {

        try (var jedis = pool.getResource()) {
            return new Watchlist(jedis.get(watchlistKey));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public void deleteWatchlist() {
        try (var jedis = pool.getResource()) {
            jedis.del(watchlistKey);
        }
    }

    @PostMapping("/{movieId}")
    public void postWatchlistMovie(@PathVariable String movieId,
                                   @RequestParam(required = false) Integer index) {

        try (var jedis = pool.getResource()) {
            var watchlist = new Watchlist(jedis.get(watchlistKey));
            watchlist.add(movieId, index);
            jedis.set(watchlistKey, watchlist.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{movieId}")
    public void deleteWatchlistMovie(@PathVariable String movieId) {

        try (var jedis = pool.getResource()) {
            var watchlist = new Watchlist(jedis.get(watchlistKey));
            watchlist.remove(movieId);
            jedis.set(watchlistKey, watchlist.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{movieId}")
    public void reorderWatchlistMovie(@PathVariable String movieId, @RequestParam Integer index) {
        try (var jedis = pool.getResource()) {
            var watchlist = new Watchlist(jedis.get(watchlistKey));
            watchlist.reorder(movieId, index);
            jedis.set(watchlistKey, watchlist.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
