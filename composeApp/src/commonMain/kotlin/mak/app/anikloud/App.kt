package mak.app.anikloud

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import mak.app.anikloud.core.common.designsystem.AppTheme
import mak.app.anikloud.core.common.ui.navigation.AppNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    AppTheme {
        Surface {
            AppNavGraph()
        }
    }
}