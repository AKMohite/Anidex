package mak.app.anikloud.feature.discover.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

@Composable
internal fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .pointerHoverIcon(PointerIcon.Hand),
        horizontalArrangement = Arrangement.Center
    ) {
        for (page in 0 until pageCount) {
            val size = if (currentPage == page) 16.dp else 6.dp
            val color = if (currentPage == page) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(alpha = 0.6f)
            Box(
                modifier = Modifier
                    .clickable { onClick(page) }
                    .padding(4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = color)
                    .size(height = 6.dp, width = size)
            )
        }
    }
}