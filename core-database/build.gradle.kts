plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")

}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.swayy.core_database"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))

    // Room
    implementation("androidx.room:room-runtime:2.5.0")
    implementation(project(mapOf("path" to ":core-network")))
    kapt("androidx.room:room-compiler:2.5.0")
    testImplementation("androidx.room:room-testing:2.5.0")
    androidTestImplementation("androidx.room:room-testing:2.5.0")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.5.0")
}