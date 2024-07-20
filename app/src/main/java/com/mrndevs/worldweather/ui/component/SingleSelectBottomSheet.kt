package com.mrndevs.worldweather.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.mrndevs.worldweather.data.source.local.model.OptionData
import com.mrndevs.worldweather.ui.theme.SP16

@Composable
fun <T> SingleSelectBottomSheet(
    onDismissRequest: (Boolean) -> Unit,
    items: List<OptionData<T>>,
    value: T? = null,
    showSheet: Boolean,
    onApply: (T) -> Unit
) {
    var selectedData: T? by remember { mutableStateOf(null) }

    LaunchedEffect(showSheet) {
        selectedData = null

        if (value != null) {
            selectedData = value
        }
    }

    val modifierSheet = if (items.size > 7) {
        Modifier.fillMaxHeight(0.8f)
    } else Modifier

    BottomSheet(
        onDismissRequest = onDismissRequest,
        isShowSheet = showSheet,
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.then(modifierSheet)
    ) {
        Column {
            LazyColumn(contentPadding = PaddingValues(start = 18.dp, end = 18.dp, bottom = 8.dp)) {
                items(count = items.size) { index ->
                    val item = items[index]
                    ItemRadio(
                        icon = item.icon,
                        label = item.label,
                        selected = selectedData == item.value,
                        onSelected = {
                            if (value != item.value) {
                                selectedData = item.value
                                onApply(item.value)
                                onDismissRequest(false)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemRadio(
    @DrawableRes icon: Int?,
    label: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        label = "BackgroundColorAnimation"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .toggleable(
                value = selected,
                role = Role.RadioButton,
                onValueChange = { onSelected() }
            )
            .padding(horizontal = 18.dp, vertical = 14.dp)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(14.dp))
        }
        Text(text = label, style = SP16, color = Color.White)
    }
}