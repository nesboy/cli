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
    val jacksonVersion: String by project
    val cliktVersion: String by project
    val log4jVersion: String by project
    val log4jKotlinVersion: String by project

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
