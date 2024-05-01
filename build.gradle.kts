plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
    //The below is for enabling KMM MongoDB Atlas Flexible Sync
    id("io.realm.kotlin") version "1.11.0"
}
