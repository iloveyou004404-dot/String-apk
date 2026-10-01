package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LiquidGlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    backgroundColor: Color = Color(0x18FFFFFF),
    borderHighlightColor: Color = Color(0x40FFFFFF),
    borderShadowColor: Color = Color(0x0AFFFFFF),
    ambientGlowColor: Color? = null,
    elevation: Dp = 8.dp,
    enableShimmer: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Optional subtle fluid shimmer offset
    val shimmerOffset by if (enableShimmer) {
        val transition = rememberInfiniteTransition(label = "LiquidShimmer")
        transition.animateFloat(
            initialValue = -300f,
            targetValue = 900f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 4000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "LiquidShimmerOffset"
        )
    } else {
        remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    }

    val glassBorderBrush = Brush.linearGradient(
        colors = listOf(
            borderHighlightColor,
            borderHighlightColor.copy(alpha = borderHighlightColor.alpha * 0.4f),
            borderShadowColor,
            borderShadowColor.copy(alpha = 0.02f)
        ),
        start = Offset(0f, 0f),
        end = Offset(400f, 400f)
    )

    val glassSurfaceBrush = Brush.linearGradient(
        colors = listOf(
            backgroundColor,
            backgroundColor.copy(alpha = (backgroundColor.alpha * 0.55f).coerceAtLeast(0.04f))
        ),
        start = Offset(0f, 0f),
        end = Offset(200f, 600f)
    )

    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                ambientColor = ambientGlowColor ?: Color(0x20000000),
                spotColor = ambientGlowColor ?: Color(0x40000000)
            )
            .clip(shape)
            .background(brush = glassSurfaceBrush, shape = shape)
            .border(
                width = 1.dp,
                brush = glassBorderBrush,
                shape = shape
            )
            .drawBehind {
                if (ambientGlowColor != null) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                ambientGlowColor.copy(alpha = 0.15f),
                                Color.Transparent
                            ),
                            center = Offset(size.width * 0.2f, size.height * 0.2f),
                            radius = size.width * 0.8f
                        )
                    )
                }
                if (enableShimmer) {
                    val shimmerBrush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.08f),
                            Color.Transparent
                        ),
                        start = Offset(shimmerOffset, 0f),
                        end = Offset(shimmerOffset + 200f, size.height)
                    )
                    drawRect(brush = shimmerBrush)
                }
            }
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = ripple(color = Color.White),
                        onClick = onClick
                    )
                } else Modifier
            ),
        content = content
    )
}
