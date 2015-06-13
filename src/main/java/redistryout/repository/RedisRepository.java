package redistryout.repository;

import java.util.Map;
import java.util.Set;

public interface RedisRepository {
    Set<String> keys();
    Map<String,String> values();
    void add(String key, String value);
    void delete(String key);
}
