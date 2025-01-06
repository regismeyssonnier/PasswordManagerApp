plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")

}

android {
    namespace = "com.example.passwordmanagerapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.passwordmanagerapp"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")

    kapt("androidx.room:room-compiler:2.5.0")

    // Coroutines pour travailler avec des tâches asynchrones
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0") // Ajoutez cette ligne pour les coroutines
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0") // Pour lifecycleScope

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}