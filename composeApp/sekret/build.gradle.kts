import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  kotlin("multiplatform")
}

kotlin {

  applyDefaultHierarchyTemplate()

//  androidTarget {
//    @OptIn(ExperimentalKotlinGradlePluginApi::class)
//    compilerOptions {
//      jvmTarget.set(JvmTarget.JVM_21)
//    }
//  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  jvm("desktop")

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    moduleName = "composeApp"
    browser {
      val rootDirPath = project.rootDir.path
      val projectDirPath = project.projectDir.path
      commonWebpackConfig {
        outputFileName = "composeApp.js"
        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
          static = (static ?: mutableListOf()).apply {
            // Serve sources to debug inside browser
            add(rootDirPath)
            add(projectDirPath)
          }
        }
      }
    }
    binaries.executable()
  }

  sourceSets {
    commonMain.dependencies {
      api("dev.datlag.sekret:sekret:${libs.versions.sekret}")
    }

    val jniNativeMain by creating {
      nativeMain.orNull?.let { dependsOn(it) } ?: dependsOn(commonMain.get())
      // androidNativeMain.orNull?.dependsOn(this)
      linuxMain.orNull?.dependsOn(this)
      mingwMain.orNull?.dependsOn(this)
      macosMain.orNull?.dependsOn(this)
    }

    val jniMain by creating {
      dependsOn(commonMain.get())
      // androidMain.orNull?.dependsOn(this)
      jvmMain.orNull?.dependsOn(this)
    }
  }
}
