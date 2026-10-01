package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun LiquidQuickGlanceRow(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Battery Glance Card
        GlanceCard(
            modifier = Modifier.weight(1f),
            title = "Battery",
            value = "88%",
            subtext = "14h 30m left",
            icon = Icons.Default.BatteryChargingFull,
            accentColor = EmeraldMint,
            progress = 0.88f,
            testTag = "glance_battery",
            onClick = { onCardClick("bat_battery") }
        )

        // Storage Glance Card
        GlanceCard(
            modifier = Modifier.weight(1f),
            title = "Storage",
            value = "42%",
            subtext = "148 GB free",
            icon = Icons.Default.Storage,
            accentColor = CyberCyan,
            progress = 0.42f,
            testTag = "glance_storage",
            onClick = { onCardClick("s_storage") }
        )

        // Memory Glance Card
        GlanceCard(
            modifier = Modifier.weight(1f),
            title = "RAM",
            value = "62%",
            subtext = "6.2 GB free",
            icon = Icons.Default.Memory,
            accentColor = AmethystViolet,
            progress = 0.62f,
            testTag = "glance_ram",
            onClick = { onCardClick("r_ram") }
        )
    }
}

@Composable
private fun GlanceCard(
    title: String,
    value: String,
    subtext: String,
    icon: ImageVector,
    accentColor: Color,
    progress: Float,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LiquidGlassSurface(
        modifier = modifier
            .height(112.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0x1CFFFFFF),
        borderHighlightColor = accentColor.copy(alpha = 0.45f),
        ambientGlowColor = accentColor.copy(alpha = 0.15f),
        elevation = 6.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(accentColor.copy(alpha = 0.18f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = accentColor,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = LiquidTextPrimary
                    )
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = LiquidTextPrimary
                    )
                )

                // Mini Liquid Progress Track
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0x22FFFFFF))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progress)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(2.dp))
                            .background(accentColor)
                    )
                }

                Text(
                    text = subtext,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        color = LiquidTextSecondary
                    ),
                    maxLines = 1
                )
            }
        }
    }
}
