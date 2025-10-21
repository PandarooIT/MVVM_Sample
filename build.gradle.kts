// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
//    id("io.realm.kotlin") version "3.0.0" apply false
}

// build.gradle.kts (root)
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.18.0")
    }
}