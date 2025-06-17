package mak.app.anikloud.core.common.designsystem

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
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
        headlineLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
            fontFamily = satoshiFont
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            fontFamily = satoshiFont
        ),
        headlineSmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            fontFamily = satoshiFont
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            fontFamily = satoshiFont
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            fontFamily = satoshiFont
        ),
        titleSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            fontFamily = satoshiFont
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            fontFamily = satoshiFont
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            fontFamily = satoshiFont
        ),
        bodySmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            fontFamily = satoshiFont
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            fontFamily = satoshiFont
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            fontFamily = satoshiFont
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp,
            fontFamily = satoshiFont
        ),
    )
}