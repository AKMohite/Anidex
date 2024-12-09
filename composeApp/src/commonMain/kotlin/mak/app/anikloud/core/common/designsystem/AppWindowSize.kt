package mak.app.anikloud.core.common.designsystem

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowWidthSizeClass


enum class AdaptiveLayoutSize {
    Compact, Medium, Expanded
}

internal val DeviceWindowSize = staticCompositionLocalOf<AdaptiveLayoutSize> {
    error("No dimensions calculated")
}

@Composable
fun AdaptiveLayout(): AdaptiveLayoutSize {
    val windowInfo = currentWindowAdaptiveInfo()
    val windowSize = windowInfo.windowSizeClass.windowWidthSizeClass
    val type = when (windowSize) {
        WindowWidthSizeClass.COMPACT -> AdaptiveLayoutSize.Compact
        WindowWidthSizeClass.MEDIUM -> AdaptiveLayoutSize.Medium
        WindowWidthSizeClass.EXPANDED -> AdaptiveLayoutSize.Expanded
        else -> AdaptiveLayoutSize.Compact
    }
    return type
}