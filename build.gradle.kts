val jimmerVersion = "0.8.181"
val redissonVersion = "3.33.0"

allprojects {
    buildscript {
        repositories {
            maven {
                url  = uri("https://maven.aliyun.com/repository/public/")
            }
            maven {
                url = uri("https://maven.aliyun.com/repository/google/")
            }
        }
    }

    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        maven { url = uri("https://maven.aliyun.com/repository/google/") }
    }
}

plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {


    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.redisson:redisson-spring-boot-starter:${redissonVersion}")
    implementation ("com.github.ben-manes.caffeine:caffeine")
    implementation ("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    annotationProcessor ("org.babyfish.jimmer:jimmer-apt:${jimmerVersion}")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
