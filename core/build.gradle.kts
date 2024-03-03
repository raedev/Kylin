plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.github.raedev.maven")
}

android {
    namespace = "androidx.kylin.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
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
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}


dependencies {
    compileOnly("androidx.core:core-ktx:1.12.0")
    compileOnly("androidx.appcompat:appcompat:1.6.1")
    compileOnly("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.9.2")
    testImplementation("com.blankj:utilcodex:1.31.1")
    testImplementation("com.google.code.gson:gson:2.10.1")
    // 可选：GSON
    compileOnly("com.google.code.gson:gson:2.10.1")
}

mavenPublishing {
    name = "androidx.kylin:core:1.0.0"
    pomUrl = "https://github.com/raedev/Kylin"
    pomName = "rae"
    pomEmail = "raedev@qq.com"
}