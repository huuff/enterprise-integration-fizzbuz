package xyz.haff.fizzbuzz

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class EnterpriseIntegrationFizzbuzzApplicationTests {

    companion object {
        @Container
        @ServiceConnection
        val rabbitMQContainer = RabbitMQContainer("rabbitmq:3-alpine")

        @Container
        @ServiceConnection
        val redisContainer = GenericContainer("redis:7.0-alpine").withExposedPorts(6379)
    }

    @Test
    fun contextLoads() {
    }

}
