plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.example.learningroomdb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.learningroomdb"
        minSdk = 31
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room dependencies
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")

    // Use Kotlin annotation processing tool (kapt) for Room
    kapt("androidx.room:room-compiler:$room_version")



    // Optional Room - Kotlin Extensions and Coroutines support
    implementation("androidx.room:room-ktx:$room_version")

    // Optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:$room_version")

    // Optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")

    // Optional - Guava support for Room
    implementation("androidx.room:room-guava:$room_version")

    // Optional - Room test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // Optional - Paging 3 integration for Room
    implementation("androidx.room:room-paging:$room_version")
}
