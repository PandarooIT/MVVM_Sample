// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
    id ("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("io.realm.kotlin") version "2.3.0" apply false
}