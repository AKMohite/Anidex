package mak.app.anikloud.data.di

import dev.datlag.sekret.NativeLoader
import java.io.File

actual fun isNativeLibLoaded(): Boolean {
    return NativeLoader.loadLibrary("sekret", System.getProperty("compose.application.resources.dir")?.let { File(it) })
}