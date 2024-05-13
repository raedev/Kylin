pluginManagement {
    repositories {
        maven { url = uri("https://maven.raeblog.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "kylin-core"
include(":core")
