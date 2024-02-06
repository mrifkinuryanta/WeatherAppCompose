plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.github.takahirom.roborazzi")
}

android {
    namespace = "com.mrndevs.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mrndevs.weatherapp"
        minSdk = 26
        targetSdk = 34
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
    kotlin {
        jvmToolchain(17)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
}

roborazzi {
    outputDir = file("build/outputs/roborazzi")
}

dependencies {

    implementation("com.github.mik3y:usb-serial-for-android:3.7.0")
    implementation("com.tagsamurai.tscomponents:libs:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:1.6.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("org.robolectric:annotations:4.11.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Compose Testing
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("androidx.compose.ui:ui-test-junit4-android")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("io.github.takahirom.roborazzi:roborazzi:1.9.0")
    testImplementation("io.github.takahirom.roborazzi:roborazzi-compose:1.9.0")
    testImplementation("io.github.takahirom.roborazzi:roborazzi-junit-rule:1.9.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.5")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}