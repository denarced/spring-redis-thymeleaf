package redistryout.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Set<String> keys() {
        return stringRedisTemplate.keys("*");
    }

    @Override
    public Map<String, String> values() {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        List<String> keys = new ArrayList<>(stringRedisTemplate.keys("*"));
        List<String> values = valueOps.multiGet(keys);
        int size = Math.min(keys.size(), values.size());
        Map<String, String> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }

    @Override
    public void add(String key, String value) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(key, value);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
