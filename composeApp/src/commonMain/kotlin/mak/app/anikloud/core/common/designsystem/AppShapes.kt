package mak.app.anikloud.core.common.designsystem

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val roundedShapes = Shapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(12.dp)
)

val cutShapes = Shapes(
    small = CutCornerShape(2.dp),
    medium = CutCornerShape(8.dp),
    large = CutCornerShape(12.dp)
)