package com.example.ui.components

import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiquidQRScannerSheet(
    accentTheme: GlassAccentTheme,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isFlashlightOn by remember { mutableStateOf(false) }

    val scanLineTransition = rememberInfiniteTransition(label = "QRScanLine")
    val scanLineY by scanLineTransition.animateFloat(
        initialValue = 0f,
        targetValue = 240f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ScanLineAnim"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color(0xFF070B14),
        scrimColor = Color(0xCC000000)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Liquid QR Lens Scanner",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = LiquidTextPrimary
                    )
                )

                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = LiquidTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scanner Viewfinder Card
            LiquidGlassSurface(
                modifier = Modifier
                    .size(260.dp)
                    .testTag("qr_viewfinder"),
                shape = RoundedCornerShape(28.dp),
                backgroundColor = Color(0x18000000),
                borderHighlightColor = accentTheme.primary.copy(alpha = 0.5f),
                ambientGlowColor = accentTheme.primary.copy(alpha = 0.25f)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Corner targeting brackets
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .size(36.dp)
                            .border(
                                width = 3.dp,
                                color = accentTheme.primary,
                                shape = RoundedCornerShape(topStart = 8.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(36.dp)
                            .border(
                                width = 3.dp,
                                color = accentTheme.primary,
                                shape = RoundedCornerShape(topEnd = 8.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                            .size(36.dp)
                            .border(
                                width = 3.dp,
                                color = accentTheme.primary,
                                shape = RoundedCornerShape(bottomStart = 8.dp)
                            )
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                            .size(36.dp)
                            .border(
                                width = 3.dp,
                                color = accentTheme.primary,
                                shape = RoundedCornerShape(bottomEnd = 8.dp)
                            )
                    )

                    // Moving laser scanning line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = scanLineY.dp)
                            .height(2.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color.Transparent,
                                        accentTheme.primary,
                                        Color.White,
                                        accentTheme.primary,
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    Text(
                        text = "Align QR Code inside frame",
                        style = TextStyle(
                            color = LiquidTextSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Flashlight and Scan trigger row
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        isFlashlightOn = !isFlashlightOn
                        Toast.makeText(
                            context,
                            if (isFlashlightOn) "Flashlight Strobe: ON" else "Flashlight Strobe: OFF",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(if (isFlashlightOn) LiquidAmber.copy(alpha = 0.25f) else Color(0x18FFFFFF))
                        .testTag("toggle_torch_btn")
                ) {
                    Icon(
                        imageVector = if (isFlashlightOn) Icons.Default.FlashOn else Icons.Default.FlashOff,
                        contentDescription = "Flashlight Toggle",
                        tint = if (isFlashlightOn) LiquidAmber else LiquidTextSecondary
                    )
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Scanned: Wi-Fi: LiquidUltra-Mesh (WPA3-Secured)", Toast.LENGTH_LONG).show()
                        onDismiss()
                    },
                    modifier = Modifier
                        .height(52.dp)
                        .testTag("simulate_scan_btn"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentTheme.primary,
                        contentColor = Color(0xFF060913)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCode,
                        contentDescription = "Scan Now",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Auto Detect Barcode", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
