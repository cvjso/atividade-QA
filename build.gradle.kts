plugins {
    kotlin("jvm") version "1.5.10"
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<JacocoReport> {
    reports {
        xml.required
        html.required
    }
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "com/school/cesar/criptocorretora/builders/",
                    "cripto-corretora/src/main/kotlin/school/cesar/criptocorretora/builders"
                )
            }
        }))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = "0.00".toBigDecimal()
                counter = "LINE"
            }
            limit {
                minimum = "0.00".toBigDecimal()
                counter = "BRANCH"
            }
        }
    }
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude(
                    "com/school/cesar/criptocorretora/builders/",
//                    "com/school/services/university/domain/validations/Constants.class",
//                    "com/school/services/university/application/universityEntryPoint.class",
//                    "com/school/services/university/application/Main.class",
//                    "com/school/services/university/application/modules/**"
                    "kotlin/school/cesar/criptocorretora/builders/CriptoBuilder.class"
                )
            }
        }))
    }
}

tasks.test {
    finalizedBy(
        "jacocoTestReport",
        "jacocoTestCoverageVerification"
    )
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
    dependsOn(tasks.test)
}