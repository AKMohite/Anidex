package mak.app.anikloud.feature.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.more_btn_txt
import coil3.compose.AsyncImage
import mak.app.anikloud.domain.model.Anime
import mak.app.anikloud.feature.discover.DiscoverSection
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun AnimeSection(
    section: DiscoverSection,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 6.dp),
        elevation = 2.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = section.type.value,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        modifier = Modifier
                            .padding(2.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        tint = MaterialTheme.colors.secondary,
                        contentDescription = stringResource(Res.string.more_btn_txt)
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 6.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(items = section.animes, key = Anime::id) { anime ->
                    AnimeCard(
                        modifier = Modifier
                            .width(140.dp)
                            .height(190.dp),
                        title = anime.title,
                        poster = anime.poster ?: anime.coverImage,
                        onAnimeClick = {}
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeCard(
    title: String,
    poster: String?,
    modifier: Modifier = Modifier,
    onAnimeClick: (Anime) -> Unit
) {
    val colorStops = arrayOf(
        0.0f to MaterialTheme.colors.surface.copy(0.20f),
        0.3f to MaterialTheme.colors.surface.copy(0.80f),
        1f to MaterialTheme.colors.surface.copy(0.95f)
    )
    Box(
        modifier = modifier
            .background(MaterialTheme.colors.surface.copy(alpha = 0.5f))
            .clip(RoundedCornerShape(6.dp))
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            maxLines = 2
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = poster,
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(colorStops = colorStops))
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(2.dp),
            text = title,
            style = MaterialTheme.typography.caption,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
