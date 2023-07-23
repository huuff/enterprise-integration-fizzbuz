package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class BuzzFlowConfiguration(
    private val buzzChannel: MessageChannel,
) {

    @Bean
    fun buzzFlow(): StandardIntegrationFlow = integrationFlow(buzzChannel) {
        handle {
            println("Buzz")
        }
    }
}