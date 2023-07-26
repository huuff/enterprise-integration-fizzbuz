package xyz.haff.fizzbuzz

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "params")
data class ParamsConfiguration @ConstructorBinding constructor(
    val produceRateMs: Long,
)