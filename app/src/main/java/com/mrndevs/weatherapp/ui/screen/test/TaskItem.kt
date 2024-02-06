package com.mrndevs.weatherapp.ui.screen.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.component.CardItem
import com.mrndevs.weatherapp.ui.screen.test.Utils.getIsCompact
import com.mrndevs.weatherapp.ui.theme.SP12
import com.mrndevs.weatherapp.ui.theme.SP14
import com.mrndevs.weatherapp.ui.theme.SP16
import com.mrndevs.weatherapp.ui.theme.W400
import com.mrndevs.weatherapp.ui.theme.W600
import com.tagsamurai.tscomponents.chip.Chip
import com.tagsamurai.tscomponents.dateformatter.DateText
import com.tagsamurai.tscomponents.model.TypeChip
import com.tagsamurai.tscomponents.theme.general_body
import com.tagsamurai.tscomponents.theme.general_grey
import com.tagsamurai.tscomponents.utils.Spacer.heightBox
import java.time.LocalDateTime

@Composable
fun TaskItem(onItemClick: () -> Unit, data: AuditTaskEntity) {
    val isCompact = LocalContext.current.getIsCompact()

    val padding: Modifier
    val titleStyle: TextStyle
    val labelStyle: TextStyle

    if (isCompact) {
        padding = Modifier
            .padding(vertical = 12.dp)
            .padding(start = 12.dp, end = 16.dp)
        titleStyle = SP12.W600
        labelStyle = SP12.W400
    } else {
        padding = Modifier.padding(16.dp)
        titleStyle = SP16.W600
        labelStyle = SP14.W400
    }

    CardItem {
        Column(
            modifier = padding
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    label = data.status.toString(),
                    type = TypeChip.BULLET,
                    severity = data.statusSeverity,
                    truncateText = false
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(0.dp)
                        .clickable {
                            onItemClick()
                        },
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null
                )
            }
            8.heightBox()
            Text(text = data.auditId.toString(), style = titleStyle)
            8.heightBox()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Chip(
                    label = data.type.toString(),
                    type = TypeChip.PILL
                )
                Spacer(modifier = Modifier.padding(start = 4.dp))
                data.categories?.let { categories ->
                    if (categories.isNotEmpty()) {
                        Chip(
                            label = categories[0]?.name.toString(),
                            type = TypeChip.PILL,
                            truncateText = true
                        )

                        if (categories.size > 1) {
                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "+${categories.size - 1} more",
                                style = labelStyle,
                                color = general_grey
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${data.durations.toString()} day(s)", style = labelStyle
                )
                Spacer(
                    modifier = Modifier.padding(end = 30.dp)
                )
            }
            8.heightBox()
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Chip(
                    label = data.group?.name.toString(),
                    type = TypeChip.PILL,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${data.progress?.completed.toString()} / ${data.progress?.total.toString()}",
                    style = labelStyle,
                    color = general_body
                )
                Spacer(
                    modifier = Modifier.padding(end = 30.dp)
                )
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Start audit: ",
                    style = labelStyle,
                    color = general_body
                )
                Text(text = "Fri 29 Sept 2023 11:00:50", style = labelStyle,)
            }
        }
    }
}