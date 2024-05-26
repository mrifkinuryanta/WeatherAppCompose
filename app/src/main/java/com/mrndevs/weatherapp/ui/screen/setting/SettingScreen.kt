package com.mrndevs.weatherapp.ui.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mrndevs.weatherapp.R
import com.mrndevs.weatherapp.ui.theme.SP18
import com.mrndevs.weatherapp.ui.theme.W700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(onNavigateUp: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Setting", style = SP18.W700) }, navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.placeholder_back_button)
                    )
                }
            })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }
}