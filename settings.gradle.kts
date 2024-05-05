rootProject.name = "cli"

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("lib") {
            version("jackson", "2.17.0")
            version("log4j", "2.23.1")

            library("clikt", "com.github.ajalt.clikt:clikt:4.4.0")
            library("conventions-kotlin", "dev.tcheng:conventions-kotlin:0.0.1")
            library(
                "jackson-dataformat-yaml",
                "com.fasterxml.jackson.dataformat",
                "jackson-dataformat-yaml"
            ).versionRef("jackson")
            library("log4j-api", "org.apache.logging.log4j", "log4j-api").versionRef("log4j")
            library("log4j-api-kotlin", "org.apache.logging.log4j:log4j-api-kotlin:1.4.0")
            library("log4j-core", "org.apache.logging.log4j", "log4j-core").versionRef("log4j")
        }
    }
}
