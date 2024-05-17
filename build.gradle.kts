// ********* CHECK BELOW URL FOR CONFIRMATION OF NEEDED DEPENDENCIES************//
//https://www.mongodb.com/docs/atlas/device-sdks/sdk/kotlin/install/#std-label-kotlin-install-kotlin-multiplatform

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.buildConfig).apply(false)
    alias(libs.plugins.sqlDelight).apply(false)
//    id("com.android.library").version("7.3.1").apply(false)
//    kotlin("android").version("1.7.10").apply(false)
    //The below is for enabling KMM MongoDB Atlas Flexible Sync
    id("io.realm.kotlin") version "1.11.0"
}
