package com.example.ui.components

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiquidProfileSheet(
    currentTheme: GlassAccentTheme,
    onSelectTheme: (GlassAccentTheme) -> Unit,
    enableShimmer: Boolean,
    onToggleShimmer: (Boolean) -> Unit,
    glassOpacity: Float,
    onGlassOpacityChange: (Float) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF090D1A),
        scrimColor = Color(0x99000000),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(42.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color(0x40FFFFFF))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(currentTheme.primary.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Smartphone,
                        contentDescription = "Device Profile",
                        tint = currentTheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Device & Liquid Studio",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = LiquidTextPrimary
                        )
                    )
                    Text(
                        text = "AIO Professional Engine v4.8",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = LiquidTextSecondary
                        )
                    )
                }

                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = LiquidTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Hardware Telemetry Spec Card
            LiquidGlassSurface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                backgroundColor = Color(0x14FFFFFF),
                borderHighlightColor = Color(0x30FFFFFF)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Hardware Specifications",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = currentTheme.primary,
                            letterSpacing = 1.sp
                        )
                    )
                    SpecItem("Device Model", "${Build.MANUFACTURER.replaceFirstChar { it.uppercase() }} ${Build.MODEL}")
                    SpecItem("Android Version", "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
                    SpecItem("Display Density", "4K Ultra-Retina (515 ppi)")
                    SpecItem("Security Patch", "March 2026 Level-1")
                    SpecItem("Kernel Architecture", System.getProperty("os.arch") ?: "arm64-v8a")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Liquid Glass Accent Customizer
            Text(
                text = "Liquid Accent Colorways",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = LiquidTextPrimary
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GlassAccentTheme.values().forEach { theme ->
                    val isSelected = theme == currentTheme
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                if (isSelected) theme.primary.copy(alpha = 0.25f)
                                else Color(0x14FFFFFF)
                            )
                            .clickable { onSelectTheme(theme) }
                            .padding(8.dp)
                            .testTag("theme_btn_${theme.name}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(theme.primary)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = theme.title.split(" ").first(),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) theme.primary else LiquidTextSecondary
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Liquid UI Refraction & Shimmer Controls
            LiquidGlassSurface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                backgroundColor = Color(0x14FFFFFF),
                borderHighlightColor = Color(0x30FFFFFF)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = "Liquid Optics Engine",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = currentTheme.primary,
                            letterSpacing = 1.sp
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Light Sheen Refraction",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = LiquidTextPrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = "Subtle dynamic fluid glow animations",
                                style = MaterialTheme.typography.bodySmall.copy(color = LiquidTextSecondary)
                            )
                        }

                        LiquidSwitch(
                            checked = enableShimmer,
                            onCheckedChange = onToggleShimmer,
                            activeColor = currentTheme.primary,
                            testTag = "toggle_shimmer"
                        )
                    }

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Glass Surface Opacity",
                                style = MaterialTheme.typography.bodyMedium.copy(color = LiquidTextPrimary)
                            )
                            Text(
                                text = "${(glassOpacity * 100).toInt()}%",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = currentTheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Slider(
                            value = glassOpacity,
                            onValueChange = onGlassOpacityChange,
                            valueRange = 0.08f..0.35f,
                            colors = SliderDefaults.colors(
                                thumbColor = currentTheme.primary,
                                activeTrackColor = currentTheme.primary,
                                inactiveTrackColor = Color(0x28FFFFFF)
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = LiquidTextSecondary)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                color = LiquidTextPrimary,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
