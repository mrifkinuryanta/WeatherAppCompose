package com.mrndevs.weatherapp.ui.component

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.ui.theme.grey
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismissRequest: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = true,
    confirmValueChange: Boolean = true,
    containerColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val window = (LocalContext.current as Activity).window
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = { confirmValueChange })

    ModalBottomSheet(
        onDismissRequest = {
            window.navigationBarColor = Color.Transparent.toArgb()
            scope.launch { sheetState.hide() }
            onDismissRequest(false)
        },
        sheetState = sheetState,
        containerColor = containerColor,
        dragHandle = { HandledContent() },
        modifier = modifier
    ) {
        window.navigationBarColor = Color.Black.toArgb()
        Column {
            BoxWithConstraints {
                content()
            }
        }
    }
}

@Composable
private fun HandledContent() {
    Surface(
        modifier = Modifier.padding(vertical = 22.dp),
        color = grey,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(Modifier.size(width = 32.dp, height = 4.dp))
    }
}