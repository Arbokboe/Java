plugins {
    id("java")
    id("application")
}

group = "domain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "domain.Game" // <- Полное имя вашего главного класса!
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8") // Более Kotlin-way
}

dependencies {
    implementation("com.googlecode.lanterna:lanterna:3.1.3")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.2")
}

tasks.test {
    useJUnitPlatform()
}
