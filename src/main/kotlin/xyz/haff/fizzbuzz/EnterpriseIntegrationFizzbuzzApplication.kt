package xyz.haff.fizzbuzz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class EnterpriseIntegrationFizzbuzzApplication

fun main(args: Array<String>) {
    runApplication<EnterpriseIntegrationFizzbuzzApplication>(*args)
}
