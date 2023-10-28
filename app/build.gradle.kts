plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "org.hotarun.dashchan_monet"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.hotarun.dashchan_monet"
        minSdk = 31
        targetSdk = 34
        versionCode = 5
        versionName = "2.0.0"
        setProperty("archivesBaseName", "Dashchan-Monet-$versionName")

    }


    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0-beta01")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

}