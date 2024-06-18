package com.mrndevs.weatherapp.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrndevs.weatherapp.data.source.local.model.SettingsEntity
import com.mrndevs.weatherapp.domain.GetSettingsUseCase
import com.mrndevs.weatherapp.domain.SaveSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val saveSettingsUseCase: SaveSettingsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()

    fun init() {
        getSettings()
    }

    private fun getSettings() {
        getSettingsUseCase().onEach { result ->
            val settings = result ?: SettingsEntity()
            _uiState.value = _uiState.value.copy(settings = settings)
        }.launchIn(viewModelScope)
    }

    fun saveSettings(settings: SettingsEntity) {
        saveSettingsUseCase(settings).onEach {
            _uiState.value = _uiState.value.copy(settings = settings)
        }.launchIn(viewModelScope)
    }
}