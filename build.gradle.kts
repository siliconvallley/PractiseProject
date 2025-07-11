// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // id("com.android.application") version "8.3.2" apply false
    // id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    // id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
    alias(libs.plugins.application) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinCompose) apply false
}