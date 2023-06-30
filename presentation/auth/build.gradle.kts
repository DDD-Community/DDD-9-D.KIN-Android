@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply(from = project.rootProject.file("gradle-scripts/base.gradle"))
apply(from = project.rootProject.file("gradle-scripts/compose.gradle"))
apply(from = project.rootProject.file("gradle-scripts/hilt.gradle"))
apply(from = project.rootProject.file("gradle-scripts/serialization.gradle"))
apply(from = project.rootProject.file("gradle-scripts/navigation.gradle"))

android {
    namespace = "com.chevit.presentation.auth"
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.kotlin.coroutine)

    implementation(libs.bundles.androidx.base)
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.bundles.compose)
}