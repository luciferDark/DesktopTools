package views

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt
@Composable
fun ChangeShapeExtendedFloatingActionButton(
    onClick:(() -> kotlin.Unit),
    text: @Composable()(() -> kotlin.Unit),
    icon: @Composable() (() -> kotlin.Unit)? = null,
    sharpEdgePercentArg: Float = 0f, roundEdgePercentArg: Float = 100f,
    modifier: Modifier? = Modifier
) {
    val sharpEdgePercent = sharpEdgePercentArg
    val roundEdgePercent = roundEdgePercentArg
    val animatedProgress = remember { Animatable(sharpEdgePercent) }
    val coroutineScope = rememberCoroutineScope()
    val progress = animatedProgress.value.roundToInt()

    val fabShape = if (progress < 0) {
        CutCornerShape(abs(progress))
    } else if (progress == roundEdgePercent.toInt()) {
        CircleShape
    } else {
        RoundedCornerShape(progress)
    }

    val changeShape: () -> Unit = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
        coroutineScope.launch {
            animatedProgress.animateTo(
                targetValue = nextTarget,
                animationSpec = TweenSpec(durationMillis = 600)
            )
        }
    }

    ExtendedFloatingActionButton(
        text = text,
        onClick = {
            changeShape()
            println("==ExtendedFloatingActionButton call onClick")
            onClick()
        },
        icon = icon,
        shape = fabShape,
        modifier = modifier ?: Modifier,
    )
}
