plugins {
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose.hotreload) apply false
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.klibs.gradle.publication) apply false
    alias(libs.plugins.klibs.gradle.rootinfo) apply false
}

apply(plugin = "ru.astrainteractive.gradleplugin.root.info")
allprojects {
    apply(plugin = "ru.astrainteractive.gradleplugin.root.info")
}