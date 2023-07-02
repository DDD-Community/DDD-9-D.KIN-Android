@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply(from = project.rootProject.file("gradle-scripts/base.gradle"))
apply(from = project.rootProject.file("gradle-scripts/hilt.gradle"))

android {
    namespace = "com.dkin.chevit.data"
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.kotlin.coroutine)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
}
