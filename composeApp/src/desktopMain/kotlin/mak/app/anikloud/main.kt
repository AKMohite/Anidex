package mak.app.anikloud

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import mak.app.anikloud.di.initKoin

fun main()  {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Anikloud",
        ) {
            App()
        }
    }
}