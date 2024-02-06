pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven {
            name = "bytesafe"
            url = uri("https://mobts.bytesafe.dev/maven/mobts/")
            credentials {
                username = "bytesafe"
                password = "01HK454G1BEQTQJGY7Z47725AA"
            }
        }
    }
}

rootProject.name = "Weather App"
include(":app")
