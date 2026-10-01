package com.example.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class InteractiveType {
    TOGGLE,
    SLIDER,
    ACTION_SHEET,
    INFO,
    QR_SCANNER
}

data class SettingItem(
    val id: String,
    val letter: Char,
    val title: String,
    val subtitle: String,
    val category: String,
    val icon: ImageVector,
    val hasToggle: Boolean = false,
    val defaultEnabled: Boolean = false,
    val accentColor: Color = Color(0xFF00F2FE),
    val badgeText: String? = null,
    val androidIntentAction: String? = null,
    val description: String = "",
    val interactiveType: InteractiveType = if (hasToggle) InteractiveType.TOGGLE else InteractiveType.ACTION_SHEET,
    val sliderValue: Float = 0.5f,
    val sliderLabel: String = ""
)
