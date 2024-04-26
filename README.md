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
    implementation("com.github.keith17766666:keithSimpleBot:${simpleBotVersion}")
}
```