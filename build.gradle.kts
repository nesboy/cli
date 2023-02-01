plugins {
    id("kotlin-conventions")
    id("detekt-conventions")
    application
}

group = "dev.tcheng"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.1")
    implementation("com.github.ajalt.clikt:clikt:3.5.1")
    implementation("org.apache.logging.log4j:log4j-api:2.19.0")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("dev.tcheng.cli.MainKt")
}
