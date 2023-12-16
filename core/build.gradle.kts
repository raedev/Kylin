plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    // optional: maven plugin
    id("com.github.raedev.maven")
}

android {
    namespace = "androidx.kylin.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
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
}

// optional: maven plugin
mavenPublishing {
    name = "androidx.kylin:core:1.0.0"
    pomUrl = "https://github.com/raedev/Kylin"
    pomName = "rae"
    pomEmail = "raedev@qq.com"
}

dependencies {
    compileOnly("androidx.core:core-ktx:1.12.0")
    compileOnly("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}