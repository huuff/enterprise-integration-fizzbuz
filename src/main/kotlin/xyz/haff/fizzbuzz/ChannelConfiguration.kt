package xyz.haff.fizzbuzz

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

@Configuration
class ChannelConfiguration(
    private val connectionFactory: AbstractConnectionFactory
) {

    @Bean
    fun inputChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("input")
        .getObject()

    @Bean
    fun fizzBuzzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("fizzbuzz")
        .getObject()

    @Bean
    fun fizzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("fizz")
        .getObject()

    @Bean
    fun buzzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("buzz")
        .getObject()

    @Bean
    fun numberChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("number")
        .getObject()

    @Bean
    fun printChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .queueName("print")
        .getObject()
}