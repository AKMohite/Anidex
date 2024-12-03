package mak.app.anikloud.data.di

import dev.datlag.sekret.NativeLoader

actual fun isNativeLibLoaded(): Boolean = NativeLoader.loadLibrary("sekret")