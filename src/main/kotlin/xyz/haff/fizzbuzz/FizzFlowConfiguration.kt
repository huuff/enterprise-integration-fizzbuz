package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FizzFlowConfiguration {

    @Bean
    fun fizzFlow(counterBroadcast: MessageChannel): StandardIntegrationFlow = integrationFlow(counterBroadcast) {
        filter<Long> { it % 3 == 0L }
        handle { println("Fizz") }
    }
}