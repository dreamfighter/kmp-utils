import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("module.publication")
    id("io.github.ttypic.swiftklib") version "0.6.4"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class,
        org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class
    )
    wasmJs {
        browser{
            testTask {
                useKarma{
                    useChrome()
                }
            }
        }
        binaries.executable()
    }
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.startup.runtime)
            }
        }
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.system.lambda)
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(npm("currency-formatter", "1.5.9"))
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.compilations {
            val main by getting {
                cinterops {
                    create("Utils")
                }
            }
        }
    }
}

android {
    namespace = "id.dreamfighter.kmp.utils"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

swiftklib {
    create("Utils") {
        path = file("native/Utils")
        packageName("id.dreamfighter.kmp.swift")
    }
}