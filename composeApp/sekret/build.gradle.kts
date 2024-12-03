plugins {
  id("com.android.library")
  kotlin("multiplatform")
}

kotlin {
  androidTarget()
  jvm()
  iosArm64 {
    binaries {
      sharedLib()
    }
  }
  iosSimulatorArm64 {
    binaries {
      sharedLib()
    }
  }
  iosX64 {
    binaries {
      sharedLib()
    }
  }
  androidNativeX86 {
    binaries {
      sharedLib()
    }
  }
  androidNativeX64 {
    binaries {
      sharedLib()
    }
  }
  androidNativeArm32 {
    binaries {
      sharedLib()
    }
  }
  androidNativeArm64 {
    binaries {
      sharedLib()
    }
  }
  linuxX64 {
    binaries {
      sharedLib()
    }
  }
  linuxArm64 {
    binaries {
      sharedLib()
    }
  }
  macosX64 {
    binaries {
      sharedLib()
    }
  }
  macosArm64 {
    binaries {
      sharedLib()
    }
  }
  mingwX64 {
    binaries {
      sharedLib()
    }
  }

  applyDefaultHierarchyTemplate()

  sourceSets {
    commonMain.dependencies {
      api("dev.datlag.sekret:sekret:2.0.0-alpha-08")
    }

    val jniNativeMain by creating {
      nativeMain.orNull?.let { dependsOn(it) } ?: dependsOn(commonMain.get())
      androidNativeMain.orNull?.dependsOn(this)
      linuxMain.orNull?.dependsOn(this)
      mingwMain.orNull?.dependsOn(this)
      macosMain.orNull?.dependsOn(this)
    }

    val jniMain by creating {
      dependsOn(commonMain.get())
      androidMain.orNull?.dependsOn(this)
      jvmMain.orNull?.dependsOn(this)
    }
  }
}
android {
  namespace = "mak.app.anikloud"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
}
