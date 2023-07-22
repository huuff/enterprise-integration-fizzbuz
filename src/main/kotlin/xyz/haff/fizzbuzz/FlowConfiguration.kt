package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.integrationFlow

@Configuration
class FlowConfiguration(
    private val redisCounterService: RedisCounterService,
) {


    @Bean
    fun flow() = integrationFlow({ redisCounterService.next() }, { poller { it.fixedRate(1000).maxMessagesPerPoll(1) }}) {
        handle {
            println(it.payload)
        }
    }

}