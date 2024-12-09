package mak.app.anikloud.core.common.designsystem

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import anikloud.composeapp.generated.resources.Res
import anikloud.composeapp.generated.resources.satoshi_black
import anikloud.composeapp.generated.resources.satoshi_bold
import anikloud.composeapp.generated.resources.satoshi_medium
import anikloud.composeapp.generated.resources.satoshi_regular
import org.jetbrains.compose.resources.Font

@Composable
internal fun provideTypography(): Typography {
    val satoshiFont = FontFamily(
        Font(Res.font.satoshi_regular, FontWeight.Normal),
        Font(Res.font.satoshi_medium, FontWeight.Medium),
        Font(Res.font.satoshi_bold, FontWeight.Bold),
        Font(Res.font.satoshi_black, FontWeight.Black)
    )

    return Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.Black,
            fontFamily = satoshiFont,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = -(0.25).sp
        ),
        h2 = TextStyle(
            fontWeight = FontWeight.Black,
            fontFamily = satoshiFont,
            fontSize = 45.sp,
            lineHeight = 52.sp
        ),
        h3 = TextStyle(
            fontWeight = FontWeight.Black,
            fontFamily = satoshiFont,
            fontSize = 36.sp,
            lineHeight = 44.sp
        ),
        h4 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = satoshiFont,
            fontSize = 28.sp,
            lineHeight = 36.sp
        ),
        h5 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = satoshiFont,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        h6 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = satoshiFont,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),
        subtitle1 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = satoshiFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle2 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = satoshiFont,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = satoshiFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = satoshiFont,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        button = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = satoshiFont,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.1.sp
        ),
        caption = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = satoshiFont,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        overline = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = satoshiFont,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.5.sp
        )
    )
}