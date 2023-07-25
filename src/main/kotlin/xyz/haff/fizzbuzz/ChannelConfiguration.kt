package xyz.haff.fizzbuzz

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

@Configuration
class ChannelConfiguration {

    @Bean
    fun inputChannel(connectionFactory: AbstractConnectionFactory): MessageChannel
        = Amqp.channel(connectionFactory)
        .queueName("input")
        .getObject()

    @Bean
    fun fizzBuzzChannel(): MessageChannel = DirectChannel()

    @Bean
    fun fizzChannel(): MessageChannel = DirectChannel()

    @Bean
    fun buzzChannel(): MessageChannel = DirectChannel()

    @Bean
    fun numberChannel(): MessageChannel = DirectChannel()

    @Bean
    fun printChannel(): MessageChannel = DirectChannel()
}