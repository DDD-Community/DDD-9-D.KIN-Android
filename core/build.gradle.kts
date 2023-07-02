@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

apply(from = project.rootProject.file("gradle-scripts/base.gradle"))

android {
    namespace = "com.dkin.chevit.core"
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.kotlin.coroutine)

    implementation(libs.bundles.androidx.base)
    implementation(libs.bundles.androidx.lifecycle)
}
