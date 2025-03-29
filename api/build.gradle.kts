import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
    enabled = true
    mainClass.set("com.unstage.api.UnstageApplication")
}

tasks.named<Jar>("jar") {
    enabled = false
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation(project(":core"))

    testImplementation(testFixtures(project(":core")))
    testFixturesImplementation(project(":core"))
    testFixturesImplementation(testFixtures(project(":core")))
}