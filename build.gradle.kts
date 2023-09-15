plugins {
    java
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "com.awan"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    testAnnotationProcessor {
        extendsFrom(annotationProcessor.get())
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    testCompileOnly {
        extendsFrom(configurations.testAnnotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-mustache")

    //LOMBOK Dependencies
    annotationProcessor("org.projectlombok:lombok")
//    compileOnly("org.projectlombok:lombok")
//    testAnnotationProcessor("org.projectlombok:lombok")
//    testCompileOnly("org.projectlombok:lombok")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testlogger {
    // pick a theme - mocha, standard, plain, mocha-parallel, standard-parallel or plain-parallel
    setTheme("mocha")

    // set to false to disable detailed failure logs
    isShowExceptions = true

    // set to false to hide stack traces
    isShowStackTraces = true

    // set to true to remove any filtering applied to stack traces
    isShowFullStackTraces = false

    // set to false to hide exception causes
    isShowCauses = true

    // set threshold in milliseconds to highlight slow tests
    slowThreshold = 2000

    // displays a breakdown of passes, failures and skips along with total duration
    isShowSummary = true

    // set to true to see simple class names
    isShowSimpleNames = false

    // set to false to hide passed tests
    isShowPassed = true

    // set to false to hide skipped tests
    isShowSkipped = true

    // set to false to hide failed tests
    isShowFailed = true

    // enable to see standard out and error streams inline with the test results
    isShowStandardStreams = false

    // set to false to hide passed standard out and error streams
    isShowPassedStandardStreams = true

    // set to false to hide skipped standard out and error streams
    isShowSkippedStandardStreams = true

    // set to false to hide failed standard out and error streams
    isShowFailedStandardStreams = true
}