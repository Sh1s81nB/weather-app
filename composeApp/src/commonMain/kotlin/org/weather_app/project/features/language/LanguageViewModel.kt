package org.weather_app.project.features.language

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.weather_app.project.core.datastore.AppDataStore
import org.weather_app.project.features.language.domain.Language
import org.weather_app.project.features.language.navigation.paramShouldNavigateBack

class LanguageViewModel(
    private val localeManager: LocaleManager,
    private val appDataStore: AppDataStore,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val shouldNavigateBack = savedStateHandle.get<Boolean>(paramShouldNavigateBack) == true
    private val _uiState = MutableStateFlow<LanguageUiState>(
        LanguageUiState.LanguageList(
            languages = Language.entries.toList(),
            selectedLanguage = getSelectedLanguage()
        )
    )
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LanguageUiState.LanguageList(
            languages = Language.entries.toList(),
            selectedLanguage = getSelectedLanguage()
        )
    )
    fun getSelectedLanguage(): Language {
        return localeManager.getLocale().let { languageCode ->
            Language.entries.find { it.details.languageCode == languageCode } ?: Language.ENGLISH
        }
    }

    fun uiEvent(event: LanguageUiEvent) {
        when (event) {
            is LanguageUiEvent.onConfirmClicked -> {
                val selectedLanguage = _uiState.value.let { state ->
                    if (state is LanguageUiState.LanguageList) state.selectedLanguage else Language.ENGLISH
                }
                localeManager.setLocale(selectedLanguage.details.languageCode)
            }
        }
    }

    fun updateSelectedLanguage(language: Language) {
        val state = _uiState.value as? LanguageUiState.LanguageList ?: return
        _uiState.value = state.copy(
            selectedLanguage = language
        )
    }

    override fun onCleared() {
        setLanguageSelectionVisited()
        super.onCleared()
    }

    fun setLanguageSelectionVisited() {
        CoroutineScope(Dispatchers.IO).launch {
            appDataStore.setLanguageSelectionVisited(true)
        }
    }
}

sealed interface LanguageUiState {
    data class LanguageList(
        val languages: List<Language>,
        val selectedLanguage: Language
    ): LanguageUiState
}

sealed interface LanguageUiEvent {
    data object onConfirmClicked: LanguageUiEvent
}

