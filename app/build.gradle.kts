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

android {
    namespace = "com.dkin.chevit.app"

    defaultConfig {
        applicationId = "com.dkin.chevit"
        versionCode = 1
        versionName = "1.0"

        manifestPlaceholders["auth0Domain"] = "team-d-kin.jp.auth0.com"
        manifestPlaceholders["auth0Scheme"] = "chevit"
    }

    buildTypes {
        release {
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
    implementation(project(":presentation:auth"))
    implementation(project(":presentation:home"))

    implementation(libs.auth0.android)

    implementation(libs.androidx.startup)
}
