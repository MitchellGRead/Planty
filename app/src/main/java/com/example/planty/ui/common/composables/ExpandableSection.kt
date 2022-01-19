package com.example.planty.ui.common.composables

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.planty.R
import com.example.planty.ui.theme.Dimen
import com.example.planty.ui.theme.Typography

const val ANIMATION_DURATION = 500

@SuppressLint("UnusedTransitionTargetStateParameter")
@ExperimentalAnimationApi
@Composable
fun ExpandableSection(
    sectionTitle: String,
    dividerColor: Color = Color.Black,
    content: @Composable () -> Unit
) {
    val (expanded, setExpanded) = remember{ mutableStateOf(false) }
    val transitionState = remember { MutableTransitionState(expanded).apply {
        targetState = !expanded
    }}
    val transition = updateTransition(targetState = transitionState, label = "transition")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = ANIMATION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = sectionTitle,
            style = Typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.40f)) {
                ExpandableSectionDivider(dividerColor)
            }

            Column(
                modifier = Modifier
                    .weight(0.20f)
                    .fillMaxWidth()
                    .clickable { setExpanded(!expanded) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropUp,
                    contentDescription = stringResource(R.string.Expandable_Section_Button),
                    modifier = Modifier.rotate(arrowRotationDegree).size(Dimen.ExpandableSectionArrowSize)
                )
            }

            Column(modifier = Modifier.weight(0.40f)) {
                ExpandableSectionDivider(dividerColor)
            }
        }
        ExpandableContent(expanded, content)
    }
}

@Composable
private fun ExpandableSectionDivider(color: Color) {
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = color,
        thickness = Dimen.ExpandableSectionDividerThickness
    )
}

@ExperimentalAnimationApi
@Composable
private fun ExpandableContent(
    expanded: Boolean,
    content: @Composable () -> Unit
) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = ANIMATION_DURATION,
                easing = FastOutLinearInEasing
            )
        )
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(ANIMATION_DURATION))
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(ANIMATION_DURATION))
    }

    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(Dimen.L)
        ) {
            content()
        }
    }
}
