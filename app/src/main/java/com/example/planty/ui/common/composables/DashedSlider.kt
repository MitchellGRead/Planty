package com.example.planty.ui.common.composables

import android.content.res.Configuration
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.PlantyTheme
import com.example.planty.ui.theme.Shapes
import kotlin.math.floor

@Composable
fun DashedSlider(
    sliderValues: List<String>,
    value: Float,
    onValueChange: (Int) -> Unit
) {
    val (sliderValue, setSliderValue) = remember { mutableStateOf(value) }
    val lineHeightDp = 10.dp
    val canvasHeight = 50.dp
    val drawPadding = with(LocalDensity.current) { 8.dp.toPx() }
    val textSize = with(LocalDensity.current) { 13.dp.toPx() }
    val lineHeightPx = with(LocalDensity.current) { lineHeightDp.toPx() }
    val textPaint = Paint().apply {
        color = if (isSystemInDarkTheme()) 0xffffffff.toInt() else 0xffff47586B.toInt()
        textAlign = Paint.Align.CENTER
        this.textSize = textSize
    }
    val activeTickColor = MaterialTheme.colors.primary
    val inactiveTickColor = activeTickColor.copy(0.24f)
    val tickWidth = 8f

    Box(contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .height(canvasHeight)
                .fillMaxWidth()
                .padding(top = canvasHeight / 2 - (lineHeightDp / 2))
        ) {
            val yStart = 0f
            val distance = (size.width - (2 * drawPadding)) / (sliderValues.size - 1)
            sliderValues.forEachIndexed { index, step ->
                if (index <= floor(sliderValue)) {
                    drawRect(
                        color = activeTickColor,
                        topLeft = Offset(x = drawPadding + index * distance, y = yStart),
                        size = Size(width = tickWidth, height = lineHeightPx)
                    )
                } else {
                    drawRect(
                        color = inactiveTickColor,
                        topLeft = Offset(x = drawPadding + index * distance, y = yStart),
                        size = Size(width = tickWidth, height = lineHeightPx)
                    )
                }

                drawContext.canvas.nativeCanvas.drawText(
                    step,
                    drawPadding + index * distance,
                    size.height,
                    textPaint
                )
            }
        }
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = sliderValue,
            valueRange = 0f..(sliderValues.size - 1).toFloat(),
            steps = sliderValues.size - 2,
            colors = customSliderColors(),
            onValueChange = {
                setSliderValue(it)
                onValueChange(it.toInt())
            })
    }
}

@Composable
private fun customSliderColors(): SliderColors = SliderDefaults.colors(
    activeTickColor = Color.Transparent,
    inactiveTickColor = Color.Transparent
)

@Preview(name = "default")
@Preview(name = "dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val sliderValues = listOf("less", "less more", "more", "more less", "most")

    PlantyTheme {
        Card(
            shape = Shapes.large,
            modifier = Modifier
                .padding(Dimen.L)
                .fillMaxWidth()
        ) {
            DashedSlider(
                sliderValues = sliderValues,
                value = (sliderValues.size / 2).toFloat(),
                onValueChange = {}
            )
        }

    }
}
