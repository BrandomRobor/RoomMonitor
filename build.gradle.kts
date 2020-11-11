buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")

        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.29.1-alpha")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    }
}