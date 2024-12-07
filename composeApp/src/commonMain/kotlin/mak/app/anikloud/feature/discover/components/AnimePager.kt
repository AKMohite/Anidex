package mak.app.anikloud.feature.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
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

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primarySurface,
                shape = RoundedCornerShape(8.dp)
            ).padding(
                bottom = 8.dp
            ).clip(
                shape = RoundedCornerShape(8.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
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
                .wrapContentHeight(),
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
    Box(
        modifier = modifier
            .aspectRatio(1.7f)
            .pointerHoverIcon(PointerIcon.Hand)
    ) {
        AsyncImage(
            modifier = modifier
                .clickable(interactionSource = interactionSource, indication = null) {
                    onAnimeClick(anime)
                }.blur(if (isPressed.value || isHovered.value) 8.dp else 0.dp),
            model = anime.coverPage,
            contentDescription = anime.title,
            contentScale = ContentScale.Crop
        )
    }
}
