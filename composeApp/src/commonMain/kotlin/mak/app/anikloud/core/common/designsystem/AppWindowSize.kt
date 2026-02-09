package mak.app.anikloud.core.common.designsystem

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND


enum class AdaptiveLayoutSize {
    Compact, Medium, Expanded, Large, ExtraLarge
}

internal val DeviceWindowSize = staticCompositionLocalOf<AdaptiveLayoutSize> {
    error("No dimensions calculated")
}

/**
 * Below breakpoints are from [androidx.compose.material3.adaptive.DpWidthSizeClasses]
 */
@Composable
fun getAdaptiveLayoutSize(): AdaptiveLayoutSize {
    val windowInfo = currentWindowAdaptiveInfo(supportLargeAndXLargeWidth = true)
    val sizeClass = windowInfo.windowSizeClass
    return when {
        sizeClass.isWidthAtLeastBreakpoint(1600) -> {
            AdaptiveLayoutSize.ExtraLarge
        }
        sizeClass.isWidthAtLeastBreakpoint(1200) -> {
            AdaptiveLayoutSize.Large
        }
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