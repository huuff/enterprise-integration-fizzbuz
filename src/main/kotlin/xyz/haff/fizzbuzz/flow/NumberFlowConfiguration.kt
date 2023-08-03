package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class NumberFlowConfiguration(
    private val numberChannel: MessageChannel,
    private val outputChannel: MessageChannel,
) {

    @Bean
    fun numberFlow(): StandardIntegrationFlow = integrationFlow(numberChannel) {
        transform<Long> { it.toString() }
        channel(outputChannel)
    }
}