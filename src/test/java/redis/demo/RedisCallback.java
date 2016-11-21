package redis.demo;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
    T doinRedis(Jedis jedis);
}
