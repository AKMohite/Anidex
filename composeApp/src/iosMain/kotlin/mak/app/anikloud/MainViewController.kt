package mak.app.anikloud

import androidx.compose.ui.window.ComposeUIViewController
import mak.app.anikloud.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}