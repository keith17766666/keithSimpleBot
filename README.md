# About
Keith's simple bot is a simple bot made by keith using jda.
# Repository
bot version: [![](https://jitpack.io/v/keith17766666/keithSimpleBot.svg)](https://jitpack.io/#keith17766666/keithSimpleBot) <br>
jda version: [![](https://img.shields.io/maven-central/v/net.dv8tion/JDA?color=blue)](https://img.shields.io/maven-central/v/net.dv8tion/JDA?color=blue) <br>
slf4j(logger) version [![Maven Central Version](https://img.shields.io/maven-central/v/org.slf4j/slf4j-api)](https://mvnrepository.com/artifact/org.slf4j/slf4j-api?repo=redhat-ga)
```gradle
repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}
dependencies {
    // other dependencies...
    implementation("net.dv8tion:JDA:${jdaVersion}")
    implementation("org.slf4j:slf4j-api:${loggerVersion}")
    implementation("com.github.keith17766666:keithSimpleBot:${simpleBotVersion}")
}
```