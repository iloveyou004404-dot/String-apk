package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun LiquidSystemHeader(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    totalCount: Int,
    matchedCount: Int,
    accentTheme: GlassAccentTheme,
    onProfileClick: () -> Unit,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        // Status Row (Simulated Flagship Status Bar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "09:41",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.5.sp,
                    color = LiquidTextSecondary
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // 5G Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0x22FFFFFF))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "5G+",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentTheme.primary
                        )
                    )
                }

                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "Wi-Fi Connected",
                    tint = LiquidTextPrimary,
                    modifier = Modifier.size(16.dp)
                )

                // Battery Pill
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0x2600F5D4))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.BatteryChargingFull,
                        contentDescription = "Battery 88%",
                        tint = EmeraldMint,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "88%",
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = LiquidTextPrimary
                        )
                    )
                }
            }
        }

        // Title and Profile Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "AIO Professional Settings",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp,
                        color = LiquidTextPrimary
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "All settings in one place",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LiquidTextSecondary,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(accentTheme.primary.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = if (searchQuery.isEmpty()) "$totalCount modules" else "$matchedCount matched",
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = accentTheme.primary
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Profile / Liquid Theme Gear Button
            LiquidGlassSurface(
                modifier = Modifier
                    .size(48.dp)
                    .testTag("profile_button"),
                shape = CircleShape,
                backgroundColor = Color(0x24FFFFFF),
                borderHighlightColor = accentTheme.primary.copy(alpha = 0.6f),
                ambientGlowColor = accentTheme.primary.copy(alpha = 0.2f),
                onClick = onProfileClick
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = "Device & UI Settings",
                        tint = accentTheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Premium Liquid Glass Search Bar
        LiquidGlassSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("search_bar"),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = Color(0x1AFFFFFF),
            borderHighlightColor = Color(0x40FFFFFF),
            elevation = 6.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Settings",
                    tint = accentTheme.primary,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("search_input"),
                    textStyle = TextStyle(
                        color = LiquidTextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    cursorBrush = SolidColor(accentTheme.primary),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Search settings, keywords, A–Z...",
                                style = TextStyle(
                                    color = LiquidTextSecondary.copy(alpha = 0.7f),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }
                        innerTextField()
                    }
                )

                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchQueryChange("") },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search",
                            tint = LiquidTextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                IconButton(
                    onClick = onFilterClick,
                    modifier = Modifier
                        .size(36.dp)
                        .testTag("filter_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filter Categories",
                        tint = if (searchQuery.isNotEmpty()) accentTheme.primary else LiquidTextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
