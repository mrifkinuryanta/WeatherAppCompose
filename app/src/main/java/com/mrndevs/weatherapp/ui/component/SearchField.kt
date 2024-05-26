package com.mrndevs.weatherapp.ui.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.ui.theme.SP16

@Composable
fun SearchFieldWithIndicator(
    onRemoveQuery: () -> Unit,
    onSearchConfirm: (String) -> Unit,
    modifier: Modifier = Modifier,
    requestFocus: Boolean? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    SearchFieldWithIndicatorContent(
        onRemoveQuery = onRemoveQuery,
        onSearchConfirm = onSearchConfirm,
        modifier = modifier,
        interactionSource = interactionSource,
        requestFocus = requestFocus
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchFieldWithIndicatorContent(
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
        onValueChange = { query = it },
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
    ) {
        TextFieldDefaults.DecorationBox(
            value = query,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_search),
                    color = Color.White,
                    style = SP16
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        query = ""
                        onRemoveQuery()
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            tint = Color.White,
                            contentDescription = stringResource(R.string.placeholder_clear_text)
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_line_24),
                        tint = Color.White,
                        contentDescription = stringResource(R.string.placeholder_search)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
            ),
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                top = 0.dp, bottom = 0.dp
            )
        )
    }
}