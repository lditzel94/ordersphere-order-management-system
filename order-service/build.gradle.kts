group = BuildConfig.GROUP
version = BuildConfig.VERSION

plugins {
    id("common-conventions")
    id("persistence.spring-data-conventions")
    id("web.spring-web-conventions")
    id("messaging.spring-kafka-conventions")
    kotlin("plugin.jpa") version "1.9.25"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework:spring-tx")
    implementation(project(":common"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

springBoot {
    buildInfo()
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    bootBuildImage {
        imageName = "${project.group}/order.service:${project.version}"
    }
}