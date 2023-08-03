package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FizzBuzzFlowConfiguration(
    private val fizzBuzzChannel: MessageChannel,
    private val outputChannel: MessageChannel,
) {

    @Bean
    fun fizzBuzzFlow(): StandardIntegrationFlow = integrationFlow(fizzBuzzChannel) {
        transform<Long> { "FizzBuzz" }
        channel(outputChannel)
    }
}