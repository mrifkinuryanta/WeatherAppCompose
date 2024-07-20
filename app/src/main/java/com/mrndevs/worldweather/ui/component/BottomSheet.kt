package com.mrndevs.worldweather.ui.component

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mrndevs.worldweather.ui.theme.accordion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismissRequest: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isShowSheet: Boolean,
    containerColor: Color = Color.White,
    skipPartiallyExpanded: Boolean = true,
    confirmValueChange: Boolean = true,
    content: @Composable () -> Unit
) {
    // Create a CoroutineScope that can be used to launch coroutines.
    val scope = rememberCoroutineScope()
    // Create a mutable state for showSheet that will be used to control the visibility of the bottom sheet.
    var showSheet by remember { mutableStateOf(false) }
    // Create a ModalBottomSheetState that will be used to manage the state of the bottom sheet.
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = { confirmValueChange }
    )

    // Launch an effect that updates the value of showSheet whenever isShowSheet changes.
    LaunchedEffect(isShowSheet) {
        if (!isShowSheet && sheetState.isVisible) {
            // If isShowSheet is false and the bottom sheet is visible, hide the bottom sheet.
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                // When the bottom sheet is hidden, set showSheet to false and call onDismissRequest with false.
                showSheet = false
                onDismissRequest(false)
            }
        } else {
            // If isShowSheet is true, set showSheet to true.
            showSheet = isShowSheet
        }
    }

    // If showSheet is true, display the bottom sheet.
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                // When the bottom sheet is dismissed, hide the bottom sheet.
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    // When the bottom sheet is hidden, set showSheet to false and call onDismissRequest with false.
                    showSheet = false
                    onDismissRequest(false)
                }
            },
            dragHandle = {
                BottomSheetDefaults.DragHandle(color = accordion)
            },
            sheetState = sheetState,
            containerColor = containerColor,
            scrimColor = MaterialTheme.colorScheme.primary,
            modifier = modifier.navigationBarsPadding()
        ) {
            content()
        }
    }
}