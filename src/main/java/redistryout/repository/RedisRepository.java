package redistryout.repository;

import java.util.Map;
import java.util.Set;

public interface RedisRepository {
    /**
     * Return a set of keys in Redis: all of them.
     *
     * @return non null.
     */
    Set<String> keys();

    /**
     * Return key-value pairs in Redis: all of them.
     *
     * @return non null.
     */
    Map<String,String> values();

    /**
     * Add key-value pair to Redis.
     */
    void add(String key, String value);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String key);
}
