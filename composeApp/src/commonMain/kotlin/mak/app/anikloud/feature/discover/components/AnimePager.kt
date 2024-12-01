package mak.app.anikloud.feature.discover.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mak.app.anikloud.domain.model.Anime

@Composable
internal fun AnimePager(
    animes: List<Anime> = emptyList(),
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(animes, key = { anime -> anime.id }) { anime ->
            Row {
                Text(anime.title)
            }
        }
    }
}