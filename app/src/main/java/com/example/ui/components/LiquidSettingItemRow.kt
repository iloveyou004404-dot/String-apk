package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SettingItem
import com.example.ui.theme.*

@Composable
fun LiquidSettingItemRow(
    item: SettingItem,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    onClick: () -> Unit,
    showDivider: Boolean = true,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(color = item.accentColor.copy(alpha = 0.3f)),
                    onClick = onClick
                )
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .testTag("setting_row_${item.id}"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Glowing Glass Icon Badge
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(item.accentColor.copy(alpha = 0.16f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.accentColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Title, Subtitle, and Badge
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = LiquidTextPrimary,
                            fontSize = 15.sp
                        )
                    )

                    if (item.badgeText != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(item.accentColor.copy(alpha = 0.2f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = item.badgeText,
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = item.accentColor
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = LiquidTextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    ),
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // Trailing Action (Toggle or Chevron)
            if (item.hasToggle) {
                LiquidSwitch(
                    checked = isEnabled,
                    onCheckedChange = onToggle,
                    activeColor = item.accentColor,
                    testTag = "switch_${item.id}"
                )
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Open ${item.title}",
                    tint = LiquidTextSecondary.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        if (showDivider) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 74.dp, end = 16.dp)
                    .height(0.8.dp)
                    .background(LiquidDivider)
            )
        }
    }
}

@Composable
fun LiquidSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    activeColor: Color,
    testTag: String,
    modifier: Modifier = Modifier
) {
    val trackBg by animateColorAsState(
        targetValue = if (checked) activeColor else Color(0x28FFFFFF),
        animationSpec = tween(durationMillis = 250),
        label = "SwitchTrackBg"
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 22.dp else 2.dp,
        animationSpec = tween(durationMillis = 250),
        label = "SwitchThumbOffset"
    )

    Box(
        modifier = modifier
            .width(48.dp)
            .height(28.dp)
            .clip(CircleShape)
            .background(trackBg)
            .clickable { onCheckedChange(!checked) }
            .padding(horizontal = 2.dp)
            .testTag(testTag),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(24.dp)
                .shadow(elevation = 4.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(if (checked) Color(0xFF060913) else Color.White)
        )
    }
}
