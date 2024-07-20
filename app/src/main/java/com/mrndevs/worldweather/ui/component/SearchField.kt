package com.mrndevs.worldweather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.mrndevs.worldweather.R
import com.mrndevs.worldweather.ui.theme.SP16
import com.mrndevs.worldweather.ui.theme.accordion

@Composable
fun SearchField(
    onRemoveQuery: () -> Unit,
    onSearchConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
    requestFocus: Boolean? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    SearchFieldContent(
        onRemoveQuery = onRemoveQuery,
        onSearchConfirm = onSearchConfirm,
        modifier = modifier,
        interactionSource = interactionSource,
        requestFocus = requestFocus
    )
}

@Composable
private fun SearchFieldContent(
    onRemoveQuery: () -> Unit,
    onSearchConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
    requestFocus: Boolean?,
    interactionSource: MutableInteractionSource
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var query by remember { mutableStateOf("") }

    LaunchedEffect(focusRequester) {
        if (requestFocus == true) {
            focusRequester.requestFocus()
        }
    }

    BasicTextField(
        value = query,
        onValueChange = { newText ->
            val newValue = when {
                newText.length >= 20 -> {
                    newText.substring(0, 20)
                }

                else -> {
                    newText
                }
            }
            query = newValue
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        interactionSource = interactionSource,
        textStyle = SP16.copy(color = Color.White),
        enabled = true,
        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        keyboardActions = KeyboardActions(onSearch = {
            onSearchConfirm(query)
            keyboardController?.hide()
            focusManager.clearFocus()
        })
    ) { innerTextField ->
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary, CircleShape),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                enabled = false
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search_line_24),
                    tint = Color.White,
                    contentDescription = stringResource(R.string.placeholder_search)
                )
            }
            Box {
                innerTextField()
                if (query.isEmpty()) {
                    Text(
                        text = stringResource(R.string.placeholder_search),
                        color = accordion,
                        style = SP16
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        query = ""
                        onRemoveQuery()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        tint = Color.White,
                        contentDescription = stringResource(R.string.placeholder_clear_text)
                    )
                }
            }
        }
    }
}