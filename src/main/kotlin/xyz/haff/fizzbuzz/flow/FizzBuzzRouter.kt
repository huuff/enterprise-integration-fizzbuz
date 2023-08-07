package xyz.haff.fizzbuzz.flow

import org.springframework.integration.annotation.Router
import org.springframework.messaging.MessageChannel
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class FizzBuzzRouter(
    private val fizzBuzzChannel: MessageChannel,
    private val fizzChannel: MessageChannel,
    private val buzzChannel: MessageChannel,
    private val numberChannel: MessageChannel,
) {
    private val FIFTEEN = BigInteger.valueOf(15)
    private val FIVE = BigInteger.valueOf(5)
    private val THREE = BigInteger.valueOf(3)

    @Router
    fun route(input: BigInteger): MessageChannel {
        return when {
            input % FIFTEEN == BigInteger.ZERO -> fizzBuzzChannel
            input % THREE == BigInteger.ZERO -> fizzChannel
            input % FIVE == BigInteger.ZERO-> buzzChannel
            else -> numberChannel
        }
    }
}