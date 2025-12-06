import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

val localProperties = File(rootDir, "local.properties").inputStream().use {
    Properties().apply { load(it) }
}

publishing {
    repositories {
        maven {
            credentials {
                username = localProperties["nexus.username"].toString()
                password = localProperties["nexus.password"].toString()
            }
            // change to point to your repo, e.g. http://my.org/repo
            url = uri("https://nexus.bandung.dev/repository/maven-bd/")
            //url = Url("https://nexus.bandung.dev/repository/maven-bd/")
        }
    }
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Kotlin Multiplatform Utils")
            description.set("Kotlin Multiplatform Utils")
            url.set("https://github.com/dreamfighter/kmp-utils")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("dreamfighter")
                    name.set("JetBrains Team")
                    organization.set("JetBrains")
                    organizationUrl.set("https://www.jetbrains.com")
                }
            }
            scm {
                url.set("https://github.com/dreamfighter/kmp-utils")
            }
        }
    }
}

signing {
    // Find all Maven publications and sign them
    useGpgCmd()
    //val secretKeyFile = System.getenv("HOME") + "/.gnupg/secring.gpg"
    // Use an absolute path string directly
    //useInMemoryPgpKeys(localProperties["gpg.signing.id"].toString(), localProperties["gpg.signing.secret"].toString(), project.file(secretKeyFile).readBytes())
    sign(publishing.publications)
}
