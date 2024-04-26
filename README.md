# About
Keith's simple bot is a simple bot made by keith using lavaplayer and jda.
# Repository
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
    implementation("dev.arbjerg:lavaplayer:${lavaPlayerVersion}")
    implementation("org.slf4j:slf4j-api:${loggerVersion}")
    implementation("com.github.keith17766666:keithSimpleBot:${simpleBotVersion}")
}
```