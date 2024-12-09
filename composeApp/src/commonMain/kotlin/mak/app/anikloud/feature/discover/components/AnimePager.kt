package mak.app.anikloud.feature.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mak.app.anikloud.domain.model.Anime

@Composable
internal fun AnimePager(
    animes: List<Anime> = emptyList(),
    modifier: Modifier = Modifier,
    onAnimeClick: (anime: Anime) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { animes.size })
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.settledPage) {
        launch {
            delay(5_500)
            val target = if (pagerState.currentPage == pagerState.pageCount - 1) 0 else pagerState.currentPage + 1
            pagerState.animateScrollToPage(target)
        }
    }

    Box(
        modifier = modifier
            .padding(
                bottom = 8.dp
            )
    ) {
        HorizontalPager(
            state = pagerState
        ) { index ->
            AnimePage(
                modifier = Modifier
                    .fillMaxWidth(),
                anime = animes[index],
                onAnimeClick = onAnimeClick
            )
        }
        PagerIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            onClick = { page ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(page)
                }
            }
        )
    }
}

@Composable
private fun AnimePage(
    anime: Anime,
    modifier: Modifier = Modifier,
    onAnimeClick: (anime: Anime) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    val colorStops = arrayOf(
        0.0f to MaterialTheme.colors.surface.copy(0.40f),
        0.3f to MaterialTheme.colors.surface.copy(0.80f),
        1f to MaterialTheme.colors.surface.copy(0.95f)
    )
    Box(
        modifier = modifier
            .aspectRatio(1.6f)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(interactionSource = interactionSource, indication = null) {
                onAnimeClick(anime)
            }.blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            model = anime.coverPage,
            contentDescription = anime.title,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .background(MaterialTheme.colors.onSurface.copy(alpha = 0.75f))
                .background(Brush.verticalGradient(colorStops = colorStops))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.Center),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.75f)
                    .clip(RoundedCornerShape(8.dp)),
                model = anime.poster,
                contentDescription = anime.title,
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(8.dp))
            Text(
                modifier = Modifier.padding(vertical = 8.dp)
                    .align(Alignment.Bottom),
                text = anime.title,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
