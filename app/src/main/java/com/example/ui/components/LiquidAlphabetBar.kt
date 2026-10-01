package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GlassAccentTheme
import com.example.ui.theme.LiquidTextPrimary
import com.example.ui.theme.LiquidTextSecondary

@Composable
fun LiquidAlphabetBar(
    selectedLetter: Char?,
    onLetterSelected: (Char?) -> Unit,
    availableLetters: Set<Char>,
    accentTheme: GlassAccentTheme,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val alphabet = ('A'..'Z').toList()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "ALL" chip
        AlphabetChip(
            text = "ALL",
            isSelected = selectedLetter == null,
            isAvailable = true,
            accentTheme = accentTheme,
            onClick = { onLetterSelected(null) },
            testTag = "alphabet_all"
        )

        alphabet.forEach { letter ->
            val isAvailable = availableLetters.contains(letter)
            val isSelected = selectedLetter == letter

            AlphabetChip(
                text = letter.toString(),
                isSelected = isSelected,
                isAvailable = isAvailable,
                accentTheme = accentTheme,
                onClick = {
                    if (isSelected) onLetterSelected(null)
                    else onLetterSelected(letter)
                },
                testTag = "alphabet_$letter"
            )
        }
    }
}

@Composable
private fun AlphabetChip(
    text: String,
    isSelected: Boolean,
    isAvailable: Boolean,
    accentTheme: GlassAccentTheme,
    onClick: () -> Unit,
    testTag: String
) {
    val interactionSource = remember { MutableInteractionSource() }

    val bg = when {
        isSelected -> accentTheme.primary
        isAvailable -> Color(0x18FFFFFF)
        else -> Color(0x08FFFFFF)
    }

    val textColor = when {
        isSelected -> Color(0xFF060913)
        isAvailable -> LiquidTextPrimary
        else -> LiquidTextSecondary.copy(alpha = 0.35f)
    }

    Box(
        modifier = Modifier
            .height(34.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .testTag(testTag)
            .clickable(
                enabled = isAvailable,
                interactionSource = interactionSource,
                indication = ripple(color = Color.White),
                onClick = onClick
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                color = textColor
            )
        )
    }
}
