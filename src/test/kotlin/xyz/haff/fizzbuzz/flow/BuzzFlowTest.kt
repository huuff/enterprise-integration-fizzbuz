package xyz.haff.fizzbuzz.flow

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.context.IntegrationFlowContext
import org.springframework.integration.test.matcher.PayloadMatcher.hasPayload
import org.springframework.messaging.support.GenericMessage
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.context.junit4.SpringRunner
import xyz.haff.fizzbuzz.flow.BuzzFlowConfiguration

@RunWith(SpringRunner::class)
@SpringJUnitConfig
@EnableIntegration
class BuzzFlowTest {

    @Autowired
    lateinit var context: IntegrationFlowContext

    @Test
    fun convertsToBuzz() {
        // ARRANGE
        val sourceChannel = DirectChannel()
        val destinationChannel = QueueChannel()
        val flow = BuzzFlowConfiguration(sourceChannel, destinationChannel).buzzFlow()
        context.registration(flow).register()
        flow.start()

        // ACT
        sourceChannel.send(GenericMessage(5L))
        val result = destinationChannel.receive()

        // ASSERT
        assertThat(result, hasPayload("Buzz"))
    }
}