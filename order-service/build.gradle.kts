import org.jetbrains.kotlin.config.JvmTarget

group = "dev.luciano"
version = "1.0.0"

plugins {
    id("common-conventions")
    id("persistence.spring-data-conventions")
    id("web.spring-web-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework:spring-tx")
    implementation(project(":common"))
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

//    processResources {
//        from("src/main/resources") {
//            include("**/*.sql")
//        }
//    }
}