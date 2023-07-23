package xyz.haff.fizzbuzz

import org.springframework.messaging.MessageChannel
import org.springframework.stereotype.Service

@Service
class FizzBuzzRouter(
    private val fizzBuzzChannel: MessageChannel,
    private val fizzChannel: MessageChannel,
    private val buzzChannel: MessageChannel,
    private val numberChannel: MessageChannel,
) {

    fun route(input: Long): MessageChannel {
        return when {
            input % 15 == 0L -> fizzBuzzChannel
            input % 3 == 0L -> fizzChannel
            input % 5 == 0L -> buzzChannel
            else -> numberChannel
        }
    }
}