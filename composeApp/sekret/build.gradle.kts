plugins {
  kotlin("multiplatform")
}

kotlin {

  applyDefaultHierarchyTemplate()

  sourceSets {
    commonMain.dependencies {
      api("dev.datlag.sekret:sekret:2.0.0-alpha-07")
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
