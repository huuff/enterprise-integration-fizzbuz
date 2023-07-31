package xyz.haff.fizzbuzz.flow

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.channel.DirectChannel
import org.springframework.messaging.MessageChannel

// TODO: These `getObject` invocations carry a comment that say it shouldn't be invoked? I should read about it
@Configuration
class ChannelConfiguration(
    private val connectionFactory: AbstractConnectionFactory
) {

    @Bean
    fun inputChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(Long::class.java)
        .queueName("input")
        .getObject()

    @Bean
    fun fizzBuzzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(Long::class.java)
        .queueName("fizzbuzz")
        .getObject()

    @Bean
    fun fizzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(Long::class.java)
        .queueName("fizz")
        .getObject()

    @Bean
    fun buzzChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(Long::class.java)
        .queueName("buzz")
        .getObject()

    @Bean
    fun numberChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(Long::class.java)
        .queueName("number")
        .getObject()

    @Bean
    fun printChannel(): MessageChannel = Amqp.channel(connectionFactory)
        .datatype(String::class.java)
        .queueName("print")
        .getObject()
}