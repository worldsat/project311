package com.example.android.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Vibrant animated wavy background with layered fluid curves, glowing neon crests, and floating orbs.
 * Shared between Nova Authentication screens (Login, Sign-Up) for cohesive brand identity.
 */
@Composable
fun NovaWavyBackground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "wave_motion")

    // Smooth continuous wave phases with varying cycle speeds
    val phase1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 7000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase1"
    )

    val phase2 by infiniteTransition.animateFloat(
        initialValue = (2 * Math.PI).toFloat(),
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase2"
    )

    val phase3 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase3"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDE9FE), // Soft rich violet
                        Color(0xFFF5F3FF),
                        Color(0xFFFAF8FF),
                        Color(0xFFEEF2FF),
                        Color(0xFFDDD6FE)  // Rich lavender
                    )
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Density-scaled wave amplitudes and ribbon thicknesses for exact cross-platform parity
            val wave1Amp = 25.dp.toPx()
            val wave1Cp1 = 42.dp.toPx()
            val wave1Cp2 = 38.dp.toPx()
            val wave1End = 22.dp.toPx()

            val wave2Amp = 32.dp.toPx()
            val wave2Cp1 = 48.dp.toPx()
            val wave2Cp2 = 44.dp.toPx()
            val wave2End = 28.dp.toPx()

            val ribbon1Amp = 35.dp.toPx()
            val ribbon1Cp1 = 52.dp.toPx()
            val ribbon1Cp2 = 50.dp.toPx()
            val ribbon1End = 30.dp.toPx()
            val ribbon1Width = 85.dp.toPx()

            val ribbon2Amp = 40.dp.toPx()
            val ribbon2Cp1 = 55.dp.toPx()
            val ribbon2Cp2 = 45.dp.toPx()
            val ribbon2End = 35.dp.toPx()
            val ribbon2Width = 70.dp.toPx()

            val botWave1Amp = 32.dp.toPx()
            val botWave1Cp1 = 48.dp.toPx()
            val botWave1Cp2 = 42.dp.toPx()
            val botWave1End = 26.dp.toPx()

            val botWave2Amp = 36.dp.toPx()
            val botWave2Cp1 = 52.dp.toPx()
            val botWave2Cp2 = 44.dp.toPx()
            val botWave2End = 32.dp.toPx()

            // ----------------------------------------------------
            // 1. TOP WAVE LAYER 1 (Deep Royal Indigo to Violet)
            // ----------------------------------------------------
            val topWave1 = Path().apply {
                reset()
                moveTo(0f, 0f)
                val startY = height * 0.17f + wave1Amp * sin(phase1)
                lineTo(0f, startY)
                val cp1X = width * 0.35f
                val cp1Y = height * 0.17f + wave1Cp1 * cos(phase1)
                val cp2X = width * 0.68f
                val cp2Y = height * 0.17f - wave1Cp2 * sin(phase1)
                val endY = height * 0.17f + wave1End * cos(phase1)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, 0f)
                close()
            }

            drawPath(
                path = topWave1,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E1B4B).copy(alpha = 0.88f),
                        Color(0xFF312E81).copy(alpha = 0.82f),
                        Color(0xFF4F46E5).copy(alpha = 0.80f),
                        Color(0xFF7C3AED).copy(alpha = 0.85f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width, height * 0.20f)
                )
            )

            // ----------------------------------------------------
            // 2. TOP WAVE LAYER 2 (Electric Violet to Magenta)
            // ----------------------------------------------------
            val topWave2 = Path().apply {
                reset()
                moveTo(0f, 0f)
                val startY = height * 0.21f + wave2Amp * cos(phase2)
                lineTo(0f, startY)
                val cp1X = width * 0.28f
                val cp1Y = height * 0.21f - wave2Cp1 * sin(phase2)
                val cp2X = width * 0.72f
                val cp2Y = height * 0.21f + wave2Cp2 * cos(phase2)
                val endY = height * 0.21f - wave2End * sin(phase2)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, 0f)
                close()
            }

            drawPath(
                path = topWave2,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4338CA).copy(alpha = 0.70f),
                        Color(0xFF6366F1).copy(alpha = 0.65f),
                        Color(0xFFA855F7).copy(alpha = 0.60f),
                        Color(0xFFEC4899).copy(alpha = 0.50f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width, height * 0.25f)
                )
            )

            // Luminous glowing neon stroke along top wave 2 crest
            val topCrestPath = Path().apply {
                reset()
                val startY = height * 0.21f + wave2Amp * cos(phase2)
                moveTo(0f, startY)
                val cp1X = width * 0.28f
                val cp1Y = height * 0.21f - wave2Cp1 * sin(phase2)
                val cp2X = width * 0.72f
                val cp2Y = height * 0.21f + wave2Cp2 * cos(phase2)
                val endY = height * 0.21f - wave2End * sin(phase2)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
            }

            drawPath(
                path = topCrestPath,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF38BDF8),
                        Color(0xFF818CF8),
                        Color(0xFFC084FC),
                        Color(0xFFF472B6)
                    ),
                    start = Offset(0f, height * 0.15f),
                    end = Offset(width, height * 0.25f)
                ),
                style = Stroke(width = 3.dp.toPx())
            )

            // ----------------------------------------------------
            // 3. MIDDLE TRANSLUCENT FLUID RIBBONS (Behind Form Card)
            // ----------------------------------------------------
            val midRibbon1 = Path().apply {
                reset()
                val startY = height * 0.46f + ribbon1Amp * sin(phase1 * 0.8f)
                moveTo(0f, startY)
                val cp1X = width * 0.35f
                val cp1Y = height * 0.46f - ribbon1Cp1 * cos(phase1 * 0.8f)
                val cp2X = width * 0.65f
                val cp2Y = height * 0.46f + ribbon1Cp2 * sin(phase1 * 0.8f)
                val endY = height * 0.46f - ribbon1End * cos(phase1 * 0.8f)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, endY + ribbon1Width)
                val bcp1X = width * 0.65f
                val bcp1Y = height * 0.46f + ribbon1Cp2 * sin(phase1 * 0.8f) + ribbon1Width
                val bcp2X = width * 0.35f
                val bcp2Y = height * 0.46f - ribbon1Cp1 * cos(phase1 * 0.8f) + ribbon1Width
                cubicTo(bcp1X, bcp1Y, bcp2X, bcp2Y, 0f, startY + ribbon1Width)
                close()
            }

            drawPath(
                path = midRibbon1,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6366F1).copy(alpha = 0.28f),
                        Color(0xFF8B5CF6).copy(alpha = 0.25f),
                        Color(0xFFEC4899).copy(alpha = 0.20f),
                        Color(0xFF06B6D4).copy(alpha = 0.22f)
                    ),
                    start = Offset(0f, height * 0.42f),
                    end = Offset(width, height * 0.52f)
                )
            )

            val midRibbon2 = Path().apply {
                reset()
                val startY = height * 0.58f + ribbon2Amp * cos(phase3)
                moveTo(0f, startY)
                val cp1X = width * 0.30f
                val cp1Y = height * 0.58f + ribbon2Cp1 * sin(phase3)
                val cp2X = width * 0.70f
                val cp2Y = height * 0.58f - ribbon2Cp2 * cos(phase3)
                val endY = height * 0.58f + ribbon2End * sin(phase3)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, endY + ribbon2Width)
                val bcp1X = width * 0.70f
                val bcp1Y = height * 0.58f - ribbon2Cp2 * cos(phase3) + ribbon2Width
                val bcp2X = width * 0.30f
                val bcp2Y = height * 0.58f + ribbon2Cp1 * sin(phase3) + ribbon2Width
                cubicTo(bcp1X, bcp1Y, bcp2X, bcp2Y, 0f, startY + ribbon2Width)
                close()
            }

            drawPath(
                path = midRibbon2,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF06B6D4).copy(alpha = 0.20f),
                        Color(0xFF4F46E5).copy(alpha = 0.26f),
                        Color(0xFFA855F7).copy(alpha = 0.22f)
                    ),
                    start = Offset(0f, height * 0.55f),
                    end = Offset(width, height * 0.65f)
                )
            )

            // ----------------------------------------------------
            // 4. BOTTOM WAVE LAYER 1 (Deep Royal Indigo Base)
            // ----------------------------------------------------
            val bottomWave1 = Path().apply {
                reset()
                moveTo(0f, height)
                val startY = height * 0.78f + botWave1Amp * sin(phase3)
                lineTo(0f, startY)
                val cp1X = width * 0.35f
                val cp1Y = height * 0.78f - botWave1Cp1 * cos(phase3)
                val cp2X = width * 0.68f
                val cp2Y = height * 0.78f + botWave1Cp2 * sin(phase3)
                val endY = height * 0.78f - botWave1End * cos(phase3)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, height)
                close()
            }

            drawPath(
                path = bottomWave1,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E1B4B).copy(alpha = 0.92f),
                        Color(0xFF2514A5).copy(alpha = 0.88f),
                        Color(0xFF4F46E5).copy(alpha = 0.85f),
                        Color(0xFF7C3AED).copy(alpha = 0.90f)
                    ),
                    start = Offset(0f, height),
                    end = Offset(width, height * 0.75f)
                )
            )

            // ----------------------------------------------------
            // 5. BOTTOM WAVE LAYER 2 (Vivid Purple to Cyan Crest)
            // ----------------------------------------------------
            val bottomWave2 = Path().apply {
                reset()
                moveTo(0f, height)
                val startY = height * 0.83f + botWave2Amp * cos(phase1)
                lineTo(0f, startY)
                val cp1X = width * 0.28f
                val cp1Y = height * 0.83f + botWave2Cp1 * sin(phase1)
                val cp2X = width * 0.72f
                val cp2Y = height * 0.83f - botWave2Cp2 * cos(phase1)
                val endY = height * 0.83f + botWave2End * sin(phase1)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
                lineTo(width, height)
                close()
            }

            drawPath(
                path = bottomWave2,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6B21A8).copy(alpha = 0.78f),
                        Color(0xFF9333EA).copy(alpha = 0.72f),
                        Color(0xFFC026D3).copy(alpha = 0.68f),
                        Color(0xFF06B6D4).copy(alpha = 0.62f)
                    ),
                    start = Offset(0f, height),
                    end = Offset(width, height * 0.80f)
                )
            )

            // Luminous glowing neon stroke along bottom wave 2 crest
            val bottomCrestPath = Path().apply {
                reset()
                val startY = height * 0.83f + botWave2Amp * cos(phase1)
                moveTo(0f, startY)
                val cp1X = width * 0.28f
                val cp1Y = height * 0.83f + botWave2Cp1 * sin(phase1)
                val cp2X = width * 0.72f
                val cp2Y = height * 0.83f - botWave2Cp2 * cos(phase1)
                val endY = height * 0.83f + botWave2End * sin(phase1)
                cubicTo(cp1X, cp1Y, cp2X, cp2Y, width, endY)
            }

            drawPath(
                path = bottomCrestPath,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF06B6D4),
                        Color(0xFFE879F9),
                        Color(0xFFF43F5E)
                    ),
                    start = Offset(0f, height * 0.83f),
                    end = Offset(width, height * 0.88f)
                ),
                style = Stroke(width = 3.dp.toPx())
            )

            // ----------------------------------------------------
            // 6. FLOATING GLOWING ORBS / ACCENTS ALONG WAVES
            // ----------------------------------------------------
            // Top orb
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF38BDF8).copy(alpha = 0.45f),
                        Color(0xFF818CF8).copy(alpha = 0.20f),
                        Color.Transparent
                    ),
                    center = Offset(width * 0.85f, height * 0.12f),
                    radius = 45.dp.toPx()
                ),
                radius = 45.dp.toPx(),
                center = Offset(width * 0.85f, height * 0.12f)
            )

            // Bottom accent orb
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFEC4899).copy(alpha = 0.40f),
                        Color(0xFF9333EA).copy(alpha = 0.18f),
                        Color.Transparent
                    ),
                    center = Offset(width * 0.15f, height * 0.88f),
                    radius = 55.dp.toPx()
                ),
                radius = 55.dp.toPx(),
                center = Offset(width * 0.15f, height * 0.88f)
            )
        }
    }
}
