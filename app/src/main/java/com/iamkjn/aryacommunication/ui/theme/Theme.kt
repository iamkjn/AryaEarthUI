package com.iamkjn.aryacommunication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = PrimaryWhite,
    secondary = SecondaryGray,
    onSecondary = PrimaryBlack,
    background = Color.Transparent,
    onBackground = PrimaryWhite,
    surface = PrimaryBlack.copy(alpha = 0.15f),
    onSurface = PrimaryWhite,
    error = PrimaryRed,
    onError = PrimaryWhite
)

val InterSemiBold = FontFamily.Default
val InterMedium = FontFamily.Default
val InterRegular = FontFamily.Default

val Typography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(
        fontFamily = InterSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = PrimaryBlue
    ),
    displayMedium = TextStyle(
        fontFamily = InterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = PrimaryBlack
    ),
    bodyLarge = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = PrimaryBlack
    ),
    bodyMedium = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = PrimaryWhite
    ),
    bodySmall = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = PrimaryWhite.copy(alpha = 0.7f)
    ),
    titleLarge = TextStyle(
        fontFamily = InterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = PrimaryWhite
    ),
    titleMedium = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = PrimaryWhite
    ),
    labelSmall = TextStyle(
        fontFamily = InterMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        color = PrimaryWhite
    ),
    labelMedium = TextStyle(
        fontFamily = InterRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        color = PrimaryWhite
    )
)

@Composable
fun MessagingScreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}