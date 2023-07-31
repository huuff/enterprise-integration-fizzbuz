package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FizzFlowConfiguration(
    private val fizzChannel: MessageChannel,
    private val printChannel: MessageChannel,
) {

    @Bean
    fun fizzFlow(): StandardIntegrationFlow = integrationFlow(fizzChannel) {
        transform<Long> { "Fizz" }
        channel(printChannel)
    }
}