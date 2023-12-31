import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("com.adarshr.test-logger") version "3.2.0"
    id("org.jetbrains.kotlinx.kover") version "0.7.3"
}

group = "xyz.haff"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.integration:spring-integration-amqp")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
    testImplementation("org.springframework.integration:spring-integration-test")
    testImplementation("org.springframework.integration:spring-integration-test-support")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:rabbitmq")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.koverHtmlReport)
}
