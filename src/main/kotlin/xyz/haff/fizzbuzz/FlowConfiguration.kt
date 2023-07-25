package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FlowConfiguration(
    private val redisCounterService: RedisCounterService,
    private val inputChannel: MessageChannel,
    private val fizzBuzzRouter: FizzBuzzRouter,
) {

    @Bean
    fun flow(
        fizzBuzzChannel: MessageChannel,
    ): StandardIntegrationFlow =
        // TODO: Receive the rate from properties
        integrationFlow({ redisCounterService.next() }, { poller { it.fixedRate(250).maxMessagesPerPoll(1) } }) {
            channel(inputChannel)
            route(fizzBuzzRouter)
        }



}