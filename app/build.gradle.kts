import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply(from = project.rootProject.file("gradle-scripts/base.gradle"))
apply(from = project.rootProject.file("gradle-scripts/compose.gradle"))
apply(from = project.rootProject.file("gradle-scripts/hilt.gradle"))
apply(from = project.rootProject.file("gradle-scripts/serialization.gradle"))
apply(from = project.rootProject.file("gradle-scripts/navigation.gradle"))
apply(from = project.rootProject.file("gradle-scripts/firebase.gradle"))

android {
    namespace = "com.dkin.chevit.app"

    defaultConfig {
        applicationId = "com.dkin.chevit"
        versionCode = 1
        versionName = "1.0"
    }
    signingConfigs {
        register("release") {
            Properties().apply {
                load(FileInputStream(rootProject.file("keystore.properties")))
            }.run {
                storeFile = file(getProperty("storeFile"))
                storePassword = getProperty("storePassword")
                keyAlias = getProperty("keyAlias")
                keyPassword = getProperty("keyPassword")
            }
        }
    }
    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation:common"))
    implementation(project(":presentation:deeplink"))
    implementation(project(":presentation:splash"))
    implementation(project(":presentation:auth"))
    implementation(project(":presentation:home"))

    implementation(libs.androidx.startup)
}
