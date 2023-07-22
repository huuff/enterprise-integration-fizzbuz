package xyz.haff.fizzbuzz

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.channel.PublishSubscribeChannel
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel

@Configuration
class FlowConfiguration(
    private val redisCounterService: RedisCounterService,
) {

    @Bean
    fun counterBroadcast(): MessageChannel = PublishSubscribeChannel()


    // TODO: Send the polled integers to RabbitMQ
    @Bean
    fun flow(counterBroadcast: MessageChannel): StandardIntegrationFlow
        = integrationFlow({ redisCounterService.next() }, { poller { it.fixedRate(1000).maxMessagesPerPoll(1) }}) {
        log()
        channel(counterBroadcast)
    }

}