package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class NumberFlowConfiguration(
    private val numberChannel: MessageChannel,
) {

    @Bean
    fun numberFlow(): StandardIntegrationFlow = integrationFlow(numberChannel) {
        handle { // TODO: How can I type this correctly?
            println(it.payload)
        }
    }
}