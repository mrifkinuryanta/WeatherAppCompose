package com.mrndevs.weatherapp.ui.screen.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.screen.test.Utils.getIsCompact
import com.mrndevs.weatherapp.ui.theme.SP14
import com.mrndevs.weatherapp.ui.theme.SP20
import com.mrndevs.weatherapp.ui.theme.W600
import com.mrndevs.weatherapp.ui.theme.primary
import com.tagsamurai.tscomponents.menu.ActionMenuItem
import com.tagsamurai.tscomponents.pullrefresh.PullRefresh
import com.tagsamurai.tscomponents.topappbar.TopAppBar
import com.tagsamurai.tscomponents.utils.Spacer.heightBox

@Composable
fun TaskScreen(
    tasks: List<AuditTaskEntity>,
    onNavigateUp: () -> Unit
) {
    val listTab = listOf("Task")

    val context = LocalContext.current
    val isCompact = context.getIsCompact()

    val size: Int
    val bottomHeight: Int
    val titleStyle: TextStyle

    if (isCompact) {
        size = 16
        bottomHeight = 14
        titleStyle = SP14.W600
    } else {
        size = 24
        bottomHeight = 12
        titleStyle = SP20.W600
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "",
                canNavigateBack = true,
                navigateUp = {
                    onNavigateUp()
                },
                menuItems = listOf(
                    ActionMenuItem.IconMenuItem.AlwaysShown(
                        title = "Search",
                        contentDescription = null,
                        onClick = {},
                        icon = com.tagsamurai.tscomponents.R.drawable.ic_search_line_24dp
                    ),
                    ActionMenuItem.IconMenuItem.AlwaysShown(
                        title = "",
                        contentDescription = null,
                        onClick = {},
                        icon = com.tagsamurai.tscomponents.R.drawable.ic_filter_line_24dp
                    )
                )
            )
        },
    ) { innerPadding ->
        PullRefresh(onRefresh = { }) {
            var selectedTab by remember { mutableIntStateOf(0) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                        .background(primary)
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = size.dp,
                        ),
                        text = "Audit",
                        style = titleStyle,
                        color = Color.White
                    )
                    size.heightBox()
                    Box(
                        modifier = Modifier.padding(
                            horizontal = size.dp,
                        )
                    ) {
                        TabList(
                            onTabChange = {
                                selectedTab = it
                            },
                            selectedTabIndex = selectedTab,
                            tabs = listTab,
                        )
                    }
                    bottomHeight.heightBox()
                }

                12.heightBox()
                repeat(tasks.size - 2) {
                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                        TaskItem(
                            onItemClick = {},
                            data = tasks[it]
                        )
                        4.heightBox()
                    }
                }
            }
        }
    }
}