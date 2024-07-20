package com.mrndevs.worldweather.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.data.source.local.model.EmptyStatusEnum
import com.mrndevs.worldweather.ui.theme.SP14
import com.mrndevs.worldweather.ui.theme.SP16
import com.mrndevs.worldweather.ui.theme.W400
import com.mrndevs.worldweather.ui.theme.W600
import com.mrndevs.worldweather.ui.theme.accordion

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    status: EmptyStatusEnum,
    onClick: (() -> Unit)? = null
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500))
        ) {
            Image(
                painter = painterResource(status.image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        val alpha by animateFloatAsState(
            if (visible) 1f else 0f,
            animationSpec = tween(durationMillis = 500)
        )

        Text(
            text = stringResource(status.title),
            color = Color.White.copy(alpha = alpha),
            style = SP16
        )
        Text(
            text = stringResource(status.placeholder),
            color = accordion.copy(alpha = alpha),
            style = SP14.W400,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        onClick?.let {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
            ) {
                Button(
                    onClick = onClick,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary.copy(alpha = 1f)
                    )
                ) {
                    val text =
                        if (status == EmptyStatusEnum.FIRST_RUN_APP) "Get Started" else "Try again"
                    Text(text = text, style = SP14.W600)
                }
            }
        }
    }
}