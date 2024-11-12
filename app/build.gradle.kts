plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.androidfolloupstest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidfolloupstest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("androidx.compose.material3:material3:1.0.0") //  la última versión disponible
    implementation ("com.google.accompanist:accompanist-pager:0.28.0") //  la última versión disponible
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.28.0") // Opcional
    implementation ("androidx.navigation:navigation-compose:2.6.0")
    implementation ("com.google.accompanist:accompanist-pager:0.25.1") // Asegúrate de tener la versión correcta
    implementation ("androidx.compose.foundation:foundation:1.3.0") // Para LazyColumn y otros componentes
    implementation ("androidx.compose.material3:material3:1.0.0") // Para los componentes de Material3
    implementation ("androidx.navigation:navigation-compose:2.6.0")

// Compose UI
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")

    // Navigation Component for Compose
    implementation("androidx.navigation:navigation-compose:2.5.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:1.3.0")

    // Carousel or Pager for Compose (Accompanist Library)
    implementation("com.google.accompanist:accompanist-pager:0.24.0-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.24.0-alpha")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}