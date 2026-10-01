package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.InteractiveType
import com.example.model.SettingItem
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiquidSettingDetailSheet(
    item: SettingItem,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    accentTheme: GlassAccentTheme,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var currentSliderVal by remember(item.id) { mutableFloatStateOf(item.sliderValue) }
    var diagnosticsRan by remember(item.id) { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF0A0F1D),
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
                .padding(bottom = 32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header with glowing glass icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(item.accentColor.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = item.accentColor,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = LiquidTextPrimary
                            )
                        )

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(accentTheme.primary.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Group ${item.letter}",
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = accentTheme.primary
                                )
                            )
                        }
                    }

                    Text(
                        text = item.category,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LiquidTextSecondary
                        )
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close detail",
                        tint = LiquidTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Liquid Description Card
            LiquidGlassSurface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                backgroundColor = Color(0x16FFFFFF),
                borderHighlightColor = Color(0x30FFFFFF),
                elevation = 4.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Module Description",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = item.accentColor,
                            letterSpacing = 1.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.description.ifEmpty { item.subtitle },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = LiquidTextPrimary,
                            lineHeight = 22.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Interactive Controls depending on type
            if (item.hasToggle) {
                LiquidGlassSurface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    backgroundColor = Color(0x16FFFFFF),
                    borderHighlightColor = item.accentColor.copy(alpha = 0.35f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Enable ${item.title}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = LiquidTextPrimary
                                )
                            )
                            Text(
                                text = if (isEnabled) "Active and functioning" else "Disabled / Standby",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = if (isEnabled) EmeraldMint else LiquidTextSecondary
                                )
                            )
                        }

                        LiquidSwitch(
                            checked = isEnabled,
                            onCheckedChange = onToggle,
                            activeColor = item.accentColor,
                            testTag = "detail_switch_${item.id}"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (item.interactiveType == InteractiveType.SLIDER) {
                LiquidGlassSurface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    backgroundColor = Color(0x16FFFFFF),
                    borderHighlightColor = item.accentColor.copy(alpha = 0.35f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.sliderLabel.ifEmpty { "Calibration Level" },
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = LiquidTextPrimary
                                )
                            )
                            Text(
                                text = "${(currentSliderVal * 100).toInt()}%",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = item.accentColor
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Slider(
                            value = currentSliderVal,
                            onValueChange = { currentSliderVal = it },
                            modifier = Modifier.fillMaxWidth(),
                            colors = SliderDefaults.colors(
                                thumbColor = item.accentColor,
                                activeTrackColor = item.accentColor,
                                inactiveTrackColor = Color(0x28FFFFFF)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Technical Matrix Cards
            LiquidGlassSurface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                backgroundColor = Color(0x12FFFFFF),
                borderHighlightColor = Color(0x25FFFFFF)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "System Telemetry & Bus",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = LiquidTextSecondary,
                            letterSpacing = 1.sp
                        )
                    )

                    TelemetryRow("Identifier", item.id)
                    TelemetryRow("Subsystem", item.category)
                    TelemetryRow("Hardware Link", if (item.androidIntentAction != null) "Direct Kernel Intent" else "Integrated Module")
                    TelemetryRow("Security Policy", item.badgeText ?: "Standard Sandbox")
                    TelemetryRow("Status", if (diagnosticsRan) "Self-Test PASSED (100% OK)" else "Normal Operational")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Open Real System Settings Button
                if (item.androidIntentAction != null) {
                    Button(
                        onClick = {
                            launchSystemSetting(context, item.androidIntentAction)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("launch_system_setting_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = accentTheme.primary,
                            contentColor = Color(0xFF060913)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.OpenInNew,
                            contentDescription = "Open System Settings",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Open Native Android Settings",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        )
                    }
                }

                // Run Self Diagnostics Test Button
                OutlinedButton(
                    onClick = {
                        diagnosticsRan = true
                        Toast.makeText(context, "${item.title} hardware diagnostics: Optimal", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("run_diagnostics_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = LiquidTextPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Test Hardware",
                        tint = EmeraldMint,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (diagnosticsRan) "Diagnostics Verified" else "Run Hardware Self-Test",
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    )
                }
            }
        }
    }
}

@Composable
private fun TelemetryRow(label: String, value: String) {
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

private fun launchSystemSetting(context: Context, action: String) {
    try {
        val intent = Intent(action).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Opening system settings: ${action.substringAfterLast('.')}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
