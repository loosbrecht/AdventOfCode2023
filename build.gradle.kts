plugins {
    kotlin("jvm") version "1.9.20"

}



dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation(kotlin("test"))
   implementation(kotlin("test-junit"))
}
sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
    }
    test{
        kotlin.srcDir("src/test/kotlin")
    }

}
repositories {
    mavenCentral()
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
