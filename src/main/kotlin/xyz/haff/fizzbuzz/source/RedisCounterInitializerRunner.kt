package xyz.haff.fizzbuzz.source

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service


@Service
class RedisCounterInitializerRunner(
    private val redisCounterService: RedisCounterService,
): CommandLineRunner {
    override fun run(vararg args: String?) {
        redisCounterService.initialize()
    }
}