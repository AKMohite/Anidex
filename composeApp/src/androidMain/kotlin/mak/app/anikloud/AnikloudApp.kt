package mak.app.anikloud

import android.app.Application
import mak.app.anikloud.di.initKoin
import org.koin.android.ext.koin.androidContext

internal class AnikloudApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AnikloudApp)
        }
    }
}