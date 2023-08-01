package xyz.haff.fizzbuzz.flow

import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
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

@RunWith(SpringRunner::class)
@SpringJUnitConfig
@EnableIntegration
class NumberFlowConfigurationTest {

    @Autowired
    lateinit var context: IntegrationFlowContext

    @Test
    fun passesNumbersAsStrings() {
        // ARRANGE
        val sourceChannel = DirectChannel()
        val targetChannel = QueueChannel()
        val flow = NumberFlowConfiguration(sourceChannel, targetChannel).numberFlow()
        context.registration(flow).register()
        flow.start()

        // ACT
        sourceChannel.send(GenericMessage(4L))
        val result = targetChannel.receive()

        // ASSERT
        assertThat(result, hasPayload("4"))
    }
}