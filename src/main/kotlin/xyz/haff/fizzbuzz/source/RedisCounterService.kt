package xyz.haff.fizzbuzz.source

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Service
import xyz.haff.fizzbuzz.config.REDIS_COUNTER_KEY

@Service
class RedisCounterService(
    private val redisTemplate: StringRedisTemplate,
    private val incBigCounter: RedisScript<String>,
) {
    private val logger = LoggerFactory.getLogger(RedisCounterService::class.java)

    fun initialize() {
        if (!redisTemplate.hasKey(REDIS_COUNTER_KEY)) {
            redisTemplate.opsForValue().set(REDIS_COUNTER_KEY, "0")
            logger.info("Initialized the counter in redis")
        } else {
            logger.info("The counter is already initialized in redis")
        }
    }


    // TODO: Handle non-existent key here and in the script
    fun next(): Long = redisTemplate.execute(incBigCounter, listOf(REDIS_COUNTER_KEY), "1").toLong()

}