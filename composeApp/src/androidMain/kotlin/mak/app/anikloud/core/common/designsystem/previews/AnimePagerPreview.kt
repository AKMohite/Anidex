package mak.app.anikloud.core.common.designsystem.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mak.app.anikloud.core.common.designsystem.AppTheme
import mak.app.anikloud.core.common.designsystem.previews.data.animeList
import mak.app.anikloud.feature.discover.components.AnimePager

@Preview
@Composable
fun AnimePagerPreview() {
    AppTheme {
        AnimePager(
            animes = animeList,
            onAnimeClick = {}
        )
    }
}