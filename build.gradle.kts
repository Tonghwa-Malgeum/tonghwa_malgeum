import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    id("java-test-fixtures")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val projectGroup: String by project
val applicationVersion: String by project
allprojects {
    group = projectGroup
    version = applicationVersion

    repositories {
        mavenCentral()
    }
}

val lombokVersion: String by project
subprojects {
    plugins.apply("java")
    plugins.apply("org.springframework.boot")
    plugins.apply("io.spring.dependency-management")
    plugins.apply("java-test-fixtures")

    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        compileOnly("org.projectlombok:lombok:${lombokVersion}")
        annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
        testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
        testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
        testFixturesCompileOnly("org.projectlombok:lombok:${lombokVersion}")
        testFixturesAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    }

    tasks.named<BootJar>("bootJar") {
        enabled = false
    }

    tasks.named<Jar>("jar") {
        enabled = true
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":api") {
    tasks.named<ProcessResources>("processResources") {
        dependsOn("copyApiConfigInProd")
    }
    tasks.register<Copy>("copyApiConfigInProd") {
        from("${project.rootDir}/unstage-config/api/main")
        include("**/*.yml")
        into("${project.rootDir}/api/src/main/resources")
    }

    tasks.named<ProcessResources>("processTestResources") {
        dependsOn("copyApiConfigInTest")
    }
    tasks.register<Copy>("copyApiConfigInTest") {
        from("${project.rootDir}/unstage-config/api/test")
        include("**/*.yml")
        into("${project.rootDir}/api/src/test/resources")
    }
}

project(":core") {
    tasks.named<ProcessResources>("processResources") {
        dependsOn("copyCoreConfigInProd")
    }
    tasks.register<Copy>("copyCoreConfigInProd") {
        from("${project.rootDir}/unstage-config/core/main")
        include("**/*.yml")
        into("${project.rootDir}/core/src/main/resources")
    }

    tasks.named<ProcessResources>("processTestResources") {
        dependsOn("copyCoreConfigInTest")
    }
    tasks.register<Copy>("copyCoreConfigInTest") {
        from("${project.rootDir}/unstage-config/core/test")
        include("**/*.yml")
        into("${project.rootDir}/core/src/test/resources")
    }
}

project(":support:monitoring") {
    tasks.named<ProcessResources>("processResources") {
        dependsOn("copyCoreConfigInProd")
    }
    tasks.register<Copy>("copyCoreConfigInProd") {
        from("${project.rootDir}/unstage-config/monitoring")
        include("**/*.yml")
        into("${project.rootDir}/support/monitoring/src/main/resources")
    }
}
