package xyz.haff.fizzbuzz.flow

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.StandardIntegrationFlow
import org.springframework.integration.dsl.integrationFlow
import org.springframework.messaging.MessageChannel
import xyz.haff.fizzbuzz.source.RedisCounterService
import xyz.haff.fizzbuzz.config.ParamsConfiguration

@Configuration
class SourceFlowConfiguration(
    private val redisCounterService: RedisCounterService,
    private val inputChannel: MessageChannel,
    private val fizzBuzzRouter: FizzBuzzRouter,
    private val params: ParamsConfiguration,
) {

    @Bean
    fun sourceFlow(
        fizzBuzzChannel: MessageChannel,
    ): StandardIntegrationFlow =
        integrationFlow({ redisCounterService.next() }, { poller { it.fixedRate(params.produceRateMs).maxMessagesPerPoll(1) } }) {
            channel(inputChannel)
            route(fizzBuzzRouter)
        }



}