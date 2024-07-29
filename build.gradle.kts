plugins {
    kotlin("jvm") version "1.8.0" // Ensure this version is compatible with Gradle 8.5
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}

application {
    mainClass.set("com.wjjmjh.kchess.view.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
