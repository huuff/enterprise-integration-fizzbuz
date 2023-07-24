package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FizzBuzzFlowConfiguration(
    private val fizzBuzzChannel: MessageChannel,
    private val printChannel: MessageChannel,
) {

    @Bean
    fun fizzBuzzFlow(): StandardIntegrationFlow = integrationFlow(fizzBuzzChannel) {
        transform<Long> { "FizzBuzz" }
        channel(printChannel)
    }
}