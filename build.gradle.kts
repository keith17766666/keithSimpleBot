plugins {
    id("java")
    id("eclipse")
    id("idea")
    id("maven-publish")
}

group = "dev.keith"
version = "v.0.1.1-alpha"
var jdaVersion = "5.0.0-beta.22"
var lavaPlayerVersion = "2.1.1"
var loggerVersion = "2.0.7"
repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("net.dv8tion:JDA:${jdaVersion}")
    implementation("dev.arbjerg:lavaplayer:${lavaPlayerVersion}")

    implementation("org.slf4j:slf4j-api:${loggerVersion}")
}


tasks.test {
    useJUnitPlatform()
}
