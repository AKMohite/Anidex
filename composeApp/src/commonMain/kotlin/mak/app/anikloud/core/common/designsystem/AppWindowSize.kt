package mak.app.anikloud.core.common.designsystem

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND


enum class AdaptiveLayoutSize {
    Compact, Medium, Expanded
}

internal val DeviceWindowSize = staticCompositionLocalOf<AdaptiveLayoutSize> {
    error("No dimensions calculated")
}

@Composable
fun AdaptiveLayout(): AdaptiveLayoutSize {
    val windowInfo = currentWindowAdaptiveInfo()
    val sizeClass = windowInfo.windowSizeClass
    return when {
        sizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
            AdaptiveLayoutSize.Expanded
        }
        sizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            AdaptiveLayoutSize.Medium
        }
        else -> {
            AdaptiveLayoutSize.Compact
        }
    }
}