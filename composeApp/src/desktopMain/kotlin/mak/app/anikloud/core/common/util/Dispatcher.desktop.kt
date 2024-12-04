package mak.app.anikloud.core.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class DesktopDispatcher: Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
    override val computation: CoroutineDispatcher
        get() = Dispatchers.Default
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
}
internal actual fun provideDispatcher(): Dispatcher = DesktopDispatcher()