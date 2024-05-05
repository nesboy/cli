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
    compileOnly(lib.conventions.kotlin)
    implementation(lib.jackson.dataformat.yaml)
    implementation(lib.clikt)
    implementation(lib.log4j.api)
    implementation(lib.log4j.api.kotlin)
    implementation(lib.log4j.core)

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("dev.tcheng.cli.MainKt")
}
