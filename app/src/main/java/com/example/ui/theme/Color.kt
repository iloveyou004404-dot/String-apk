package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Liquid Glass Palette - Obsidian & Midnight Bases
val LiquidBackgroundDark = Color(0xFF060913)
val LiquidSurfaceDark = Color(0x18FFFFFF)
val LiquidSurfaceElevated = Color(0x24FFFFFF)
val LiquidSurfaceUltraFrosted = Color(0x2EFFFFFF)
val LiquidCardBorderTop = Color(0x55FFFFFF)
val LiquidCardBorderBottom = Color(0x12FFFFFF)

// Accent Themes
val CyberCyan = Color(0xFF00F2FE)
val CyanGlow = Color(0xFF4FACFE)
val EmeraldMint = Color(0xFF00F5D4)
val AmethystViolet = Color(0xFFB5179E)
val NeonPurple = Color(0xFF7209B7)
val ElectricBlue = Color(0xFF4361EE)
val LiquidAmber = Color(0xFFFF9E00)
val LiquidRuby = Color(0xFFFF0054)

// Neutral Text & Icons
val LiquidTextPrimary = Color(0xFFF8FAFC)
val LiquidTextSecondary = Color(0xFF94A3B8)
val LiquidTextTertiary = Color(0xFF64748B)
val LiquidDivider = Color(0x1FFFFFFF)

// Glass Highlights
val GlassSpecularHighlight = Color(0x80FFFFFF)
val GlassShadowTint = Color(0x80000000)

enum class GlassAccentTheme(
    val title: String,
    val primary: Color,
    val secondary: Color,
    val glowBrush: Brush
) {
    CYBER_CYAN(
        title = "Cyber Cyan",
        primary = CyberCyan,
        secondary = CyanGlow,
        glowBrush = Brush.horizontalGradient(listOf(CyberCyan, CyanGlow))
    ),
    AMETHYST_NEBULA(
        title = "Amethyst Nebula",
        primary = AmethystViolet,
        secondary = NeonPurple,
        glowBrush = Brush.horizontalGradient(listOf(AmethystViolet, NeonPurple))
    ),
    EMERALD_AURORA(
        title = "Emerald Aurora",
        primary = EmeraldMint,
        secondary = ElectricBlue,
        glowBrush = Brush.horizontalGradient(listOf(EmeraldMint, ElectricBlue))
    ),
    OBSIDIAN_CHROME(
        title = "Obsidian Chrome",
        primary = Color(0xFFE2E8F0),
        secondary = Color(0xFF94A3B8),
        glowBrush = Brush.horizontalGradient(listOf(Color(0xFFF1F5F9), Color(0xFF64748B)))
    )
}
