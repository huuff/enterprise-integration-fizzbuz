package xyz.haff.fizzbuzz.flow

import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.integration.channel.DirectChannel
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringJUnitConfig
class FizzBuzzRouterTest {
    private val fizzChanel = DirectChannel()
    private val buzzChannel = DirectChannel()
    private val fizzBuzzChannel = DirectChannel()
    private val numberChannel = DirectChannel()
    private val router = FizzBuzzRouter(
        fizzBuzzChannel = fizzBuzzChannel,
        fizzChannel = fizzChanel,
        buzzChannel = buzzChannel,
        numberChannel = numberChannel,
    )

    @Test
    fun routesFizz() {
        val channel = router.route(3L)

        assertSame(fizzChanel, channel)
    }

    @Test
    fun routesBuzz() {
        val channel = router.route(5L)

        assertSame(buzzChannel, channel)
    }

    @Test
    fun routesFizzBuzz() {
        val channel = router.route(15L)

        assertSame(fizzBuzzChannel, channel)
    }

    @Test
    fun routesNumber() {
        val channel = router.route(7L)

        assertSame(numberChannel, channel)
    }
}