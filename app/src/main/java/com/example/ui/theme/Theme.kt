package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LiquidColorScheme = darkColorScheme(
    primary = CyberCyan,
    onPrimary = LiquidBackgroundDark,
    primaryContainer = Color(0x3300F2FE),
    onPrimaryContainer = Color(0xFFE0F7FF),
    secondary = CyanGlow,
    onSecondary = LiquidBackgroundDark,
    tertiary = EmeraldMint,
    background = LiquidBackgroundDark,
    onBackground = LiquidTextPrimary,
    surface = LiquidBackgroundDark,
    onSurface = LiquidTextPrimary,
    surfaceVariant = Color(0x1AFFFFFF),
    onSurfaceVariant = LiquidTextSecondary,
    outline = LiquidCardBorderTop,
    outlineVariant = LiquidDivider
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Force futuristic liquid dark mode for professional flagship look
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = LiquidColorScheme,
        typography = Typography,
        content = content
    )
}
