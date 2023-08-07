package xyz.haff.fizzbuzz.source

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.core.script.RedisScript

@Configuration
class RedisScriptConfiguration {

    @Bean
    fun incBigCounter(): RedisScript<String> {
        val resource = ClassPathResource("inc-counter.lua")
        return RedisScript.of(resource, String::class.java)
    }
}