package com.mrndevs.weatherapp.ui.screen.test

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.screen.test.Utils.getWidthSizeClass
import com.tagsamurai.tscomponents.theme.SP12
import com.tagsamurai.tscomponents.theme.SP16
import com.tagsamurai.tscomponents.theme.primary

@Composable
fun TabList(
    modifier: Modifier = Modifier,
    onTabChange: (index: Int) -> Unit,
    tabs: List<String>,
    selectedTabIndex: Int = 0
) {
    TabListContent(
        modifier = modifier,
        onTabChange = onTabChange,
        tabs = tabs,
        selectedTabIndex = selectedTabIndex
    )
}

@Composable
internal fun TabListContent(
    modifier: Modifier,
    onTabChange: (index: Int) -> Unit,
    tabs: List<String>,
    selectedTabIndex: Int
) {
    val context = LocalContext.current
    val height: Dp
    val minWidth: Dp
    val shape: Dp
    val textStyle: TextStyle

    val (selected, setSelected) = remember { mutableIntStateOf(selectedTabIndex) }

    when (context.getWidthSizeClass()) {
        WindowWidthSizeClass.Compact -> {
            height = 44.dp
            minWidth = 80.dp
            shape = 5.dp
            textStyle = SP12
        }

        else -> {
            height = 56.dp
            minWidth = 174.dp
            shape = 10.dp
            textStyle = SP16
        }
    }

    ScrollableTabRow(
        selectedTabIndex = selected,
        containerColor = Color.White,
        modifier = modifier.clip(RoundedCornerShape(shape)),
        edgePadding = 0.dp,
        indicator = {},
        divider = {}
    ) {
        tabs.mapIndexed { index, text ->
            val isSelected = index == selected

            val tabBackgroundColor: Color by animateColorAsState(
                targetValue = if (isSelected) {
                    primary
                } else {
                    Color.White
                },
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
            )
            Box(
                modifier = Modifier
                    .height(height)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(shape))
                    .background(tabBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Tab(
                    text = {
                        Text(
                            text = text,
                            style = textStyle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.widthIn(min = minWidth)
                        )
                    },
                    selected = isSelected,
                    selectedContentColor = Color.White,
                    unselectedContentColor = primary,
                    onClick = {
                        setSelected(index)
                        onTabChange(index)
                    }
                )
            }
        }
    }
}