package xyz.haff.fizzbuzz.flow

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.amqp.dsl.Amqp
import org.springframework.integration.amqp.dsl.AmqpMessageChannelSpec
import java.math.BigInteger

@Configuration
class ChannelConfiguration(
    private val connectionFactory: AbstractConnectionFactory
) {

    @Bean
    fun inputChannel(): AmqpMessageChannelSpec<*, *> = Amqp.channel(connectionFactory)
        .datatype(BigInteger::class.java)
        .queueName("input")

    @Bean
    fun fizzBuzzChannel(): AmqpMessageChannelSpec<*, *>  = Amqp.channel(connectionFactory)
        .datatype(BigInteger::class.java)
        .queueName("fizzbuzz")

    @Bean
    fun fizzChannel(): AmqpMessageChannelSpec<*, *>  = Amqp.channel(connectionFactory)
        .datatype(BigInteger::class.java)
        .queueName("fizz")

    @Bean
    fun buzzChannel(): AmqpMessageChannelSpec<*, *>  = Amqp.channel(connectionFactory)
        .datatype(BigInteger::class.java)
        .queueName("buzz")

    @Bean
    fun numberChannel(): AmqpMessageChannelSpec<*, *>  = Amqp.channel(connectionFactory)
        .datatype(BigInteger::class.java)
        .queueName("number")

    @Bean
    fun outputChannel(): AmqpMessageChannelSpec<*, *>  = Amqp.publishSubscribeChannel(connectionFactory)
        .datatype(String::class.java)
        .queueName("output")
}