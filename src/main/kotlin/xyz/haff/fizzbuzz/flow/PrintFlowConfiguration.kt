package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class PrintFlowConfiguration(
    private val outputChannel: MessageChannel,
) {

    @Bean
    fun printFlow(): StandardIntegrationFlow = integrationFlow(outputChannel) {
        handle {
            println(it.payload)
        }
    }
}