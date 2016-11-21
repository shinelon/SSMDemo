package redis.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisUtil {
    private static Logger logger = Logger.getLogger(RedisUtil.class);

    private static JedisSentinelPool JedisSentinelPool;

    static {
        try {
            loadProperties();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static String get(final String key) {
        return execute(new RedisCallback<String>() {
            public String doinRedis(Jedis jedis) {
                return jedis.get(key);
            }
        });

    }

    public static List<String> hmget(final String key, final String[] fields) {
        return execute(new RedisCallback<List<String>>() {

            public List<String> doinRedis(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }

    public static String set(final String key, final String value) {
        return execute(new RedisCallback<String>() {

            public String doinRedis(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    private static Jedis getJedis() {
        return JedisSentinelPool.getResource();
    }

    private static void loadProperties() throws IOException {
        Properties props = FileUtil.loadProps("/redis.properties");
        String host = props.getProperty("redis.host");
        String password = props.getProperty("redis.password");
        String masterName = props.getProperty("redis.master.name");
        int dbindex = Integer.parseInt(props.getProperty("redis.dbIndex"));
        int timeout = Integer.parseInt(props.getProperty("redis.connection.timeout"));
        int maxTotal = Integer.parseInt(props.getProperty("redis.pool.maxTotal"));
        int minIdle = Integer.parseInt(props.getProperty("redis.pool.minIdle"));
        int maxIdle = Integer.parseInt(props.getProperty("redis.pool.maxIdle"));
        int maxWaitMillis = Integer.parseInt(props.getProperty("redis.pool.maxWaitMillis"));
        boolean blockWhenExhausted = Boolean.parseBoolean(props.getProperty("redis.pool.blockWhenExhausted"));
        boolean testOnBorrow = Boolean.parseBoolean(props.getProperty("redis.pool.testOnBorrow"));
        boolean testOnReturn = Boolean.parseBoolean(props.getProperty("redis.pool.testOnReturn"));
        boolean testWhileIdle = Boolean.parseBoolean(props.getProperty("redis.pool.testWhileIdle"));

        int minEvictableIdleTimeMillis = Integer.parseInt(props.getProperty("redis.pool.minEvictableIdleTimeMillis"));
        int timeBetweenEvictionRunsMillis = Integer
                .parseInt(props.getProperty("redis.pool.timeBetweenEvictionRunsMillis"));
        int numTestsPerEvictionRun = Integer.parseInt(props.getProperty("redis.pool.numTestsPerEvictionRun"));
        int softMinEvictableIdleTimeMillis = Integer
                .parseInt(props.getProperty("redis.pool.softMinEvictableIdleTimeMillis"));
        boolean lifo = Boolean.parseBoolean(props.getProperty("redis.pool.lifo"));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
        jedisPoolConfig.setLifo(lifo);

        Set<String> sentinels = new HashSet<String>();
        sentinels.addAll(Arrays.asList(host.split(";")));

        JedisSentinelPool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, timeout, password, dbindex);
    }

    static <T> T execute(RedisCallback<T> action) {
        Jedis jedis = getJedis();
        try {
            return action.doinRedis(jedis);
        } finally {
            jedis.close();
        }

    }
}
