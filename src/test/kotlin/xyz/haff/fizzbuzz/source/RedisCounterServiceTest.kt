package xyz.haff.fizzbuzz.source

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.StringRedisTemplate
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import xyz.haff.fizzbuzz.config.REDIS_COUNTER_KEY

@DataRedisTest
@Testcontainers
@Import(RedisCounterService::class)
class RedisCounterServiceTest {

    companion object {

        // TODO: Repeated for the EnterpriseIntegrationFizzbuzzApplicationTests... try to reuse it somehow
        @Container
        @ServiceConnection
        val redisContainer = GenericContainer("redis:7.0-alpine").withExposedPorts(6379)
    }

    @Autowired
    lateinit var redisCounterService: RedisCounterService

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    @Test
    fun initialize() {
        // ARRANGE
        redisTemplate.delete(REDIS_COUNTER_KEY)

        // SANITY-CHECK
        assertFalse(redisTemplate.hasKey(REDIS_COUNTER_KEY))

        // ACT
        redisCounterService.initialize()

        // ASSERT
        assertEquals("0", redisTemplate.opsForValue()[REDIS_COUNTER_KEY])
    }


    @Test
    fun next() {
        // ARRANGE
        redisTemplate.opsForValue()[REDIS_COUNTER_KEY] = "0"

        // ACT
        val result = redisCounterService.next()

        // ASSERT
        assertEquals(1L, result)
    }
}