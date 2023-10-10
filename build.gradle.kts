plugins {
    id("dev.tcheng.conventions-kotlin.kotlin") version "0.0.1"
    id("dev.tcheng.conventions-kotlin.detekt") version "0.0.1"
    application
}

group = "dev.tcheng"
version = "0.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    val jacksonVersion: String by project
    val cliktVersion: String by project
    val log4jVersion: String by project
    val log4jKotlinVersion: String by project

    compileOnly("dev.tcheng.conventions-kotlin:plugin:0.0.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:$log4jKotlinVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("dev.tcheng.cli.MainKt")
}
