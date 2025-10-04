import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation("org.xerial:sqlite-jdbc:3.41.2.2")
            implementation("org.dizitart:potassium-nitrite:4.3.2")
            implementation("org.dizitart:nitrite-mvstore-adapter:4.3.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.example.snipsnip.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Exe, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Pkg, TargetFormat.AppImage, TargetFormat.Rpm)
            packageName = "org.example.snipsnip"
            packageVersion = "1.0.0"
        }
    }

tasks.withType<JavaExec> {
    jvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
    }
}
