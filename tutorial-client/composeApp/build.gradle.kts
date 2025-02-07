import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)

//    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        val ktor_version = "2.3.12"
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
//            implementation("org.slf4j:slf4j-android:1.7.36")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.kotlinx.coroutines.core)

            implementation("io.ktor:ktor-client-core:$ktor_version")
            implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            implementation("io.ktor:ktor-client-cio:$ktor_version")
            implementation("io.ktor:ktor-client-logging:$ktor_version")
            implementation("io.ktor:ktor-client-serialization:$ktor_version")
            implementation("io.ktor:ktor-serialization-kotlinx:$ktor_version")
//            implementation("io.ktor:ktor-client-logging:$ktor_version")


            implementation("org.slf4j:slf4j-api:2.0.7")
//            implementation("ch.qos.logback:logback-classic:1.4.11")
//            implementation("org.slf4j:slf4j-nop:2.0.7")
//            implementation("org.slf4j:slf4j-log4j12:2.0.7")
                implementation("org.slf4j:slf4j-simple:2.0.7")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation("io.ktor:ktor-client-cio:2.3.12")
            implementation("io.ktor:ktor-serialization-kotlinx:$ktor_version")
            implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:${ktor_version}")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
        }
    }
}

android {
    namespace = "org.test.tutorial_client"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.test.tutorial_client"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "org.test.tutorial_client.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.test.tutorial_client"
            packageVersion = "1.0.0"
        }
    }
}
