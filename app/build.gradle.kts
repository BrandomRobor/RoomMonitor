plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.integrative.roommonitor"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.10")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    // Navigation dependencies
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.29.1-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.29.1-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha02")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.0.0-alpha09")

    // LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.5")

    // Navigation drawer
    implementation("com.mikepenz:materialdrawer:8.1.8")
    implementation("com.mikepenz:materialdrawer-nav:8.1.8")
    implementation("com.mikepenz:materialdrawer-iconics:8.1.8")

    // Icons library
    implementation("com.mikepenz:iconics-core:5.0.3")
    implementation("com.mikepenz:iconics-views:5.0.3")
    implementation("com.mikepenz:community-material-typeface:5.3.45.1-kotlin@aar")

    // Swipe to refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Socket.io client
    implementation("io.socket:socket.io-client:1.0.0") {
        exclude("org.json", "json")
    }

    // Preference library
    implementation("androidx.preference:preference-ktx:1.1.1")
}