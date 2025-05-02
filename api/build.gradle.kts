import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
    enabled = true
    mainClass.set("com.unstage.api.UnstageApplication")
}

tasks.named<Jar>("jar") {
    enabled = false
}

val restAssuredVersion: String by project
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")

    implementation(project(":core"))
    implementation(project(":support:monitoring"))
    implementation(project(":support:logging"))

    testFixturesImplementation(project(":api"))
    testImplementation(testFixtures(project(":core")))
    testFixturesImplementation(project(":core"))
    testFixturesImplementation(testFixtures(project(":core")))
}