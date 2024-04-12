package com.example.strats.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.strats.R

val Anton = FontFamily(
    Font(R.font.anton_regular)
)

val Poppins = FontFamily(
    Font(R.font.poppins_extra_light, FontWeight.Light),
    Font(R.font.poppins_extra_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.poppins_light),
    Font(R.font.poppins_light_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.poppins_semibold, FontWeight.Bold),
    Font(R.font.poppins_semibold_italic, FontWeight.Bold, FontStyle.Italic)
)

val Ubuntu = FontFamily(
    Font(R.font.ubuntu_light),
    Font(R.font.ubuntu_light, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.ubuntu_medium, FontWeight.Bold),
    Font(R.font.ubuntu_medium_italic, FontWeight.Bold, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Anton,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Anton,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing  = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)