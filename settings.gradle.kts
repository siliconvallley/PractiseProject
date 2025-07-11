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
    }

    // 引入Catalogs
    versionCatalogs {
        create("libs") {
            from(files("./libs.versions.toml"))
        }
    }
}

rootProject.name = "PractiseProject"
include(":app")
include(":libs:lib-algorithm")
include(":modules:pp-algorithm")
