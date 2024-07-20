package com.mrndevs.weatherapp.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

@Composable
fun BaseDialog(
    onDismissRequest: (Boolean) -> Unit,
    isShowDialog: Boolean,
    content: @Composable () -> Unit
) {
    val properties = DialogProperties(
        dismissOnClickOutside = false,
        usePlatformDefaultWidth = false
    )
    if (isShowDialog) {
        Dialog(
            onDismissRequest = { onDismissRequest(false) },
            properties = properties
        ) {
            (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
            content()
        }
    }
}