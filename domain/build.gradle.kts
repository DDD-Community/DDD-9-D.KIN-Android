@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

apply(from = project.rootProject.file("gradle-scripts/java.gradle"))
apply(from = project.rootProject.file("gradle-scripts/serialization.gradle"))

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.kotlin.coroutine)
}
