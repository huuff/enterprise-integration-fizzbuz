package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel
import java.math.BigInteger

@Configuration
class FizzFlowConfiguration(
    private val fizzChannel: MessageChannel,
    private val outputChannel: MessageChannel,
) {

    @Bean
    fun fizzFlow(): StandardIntegrationFlow = integrationFlow(fizzChannel) {
        transform<BigInteger> { "Fizz" }
        channel(outputChannel)
    }
}