package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.SettingsRepository
import com.example.model.SettingItem
import com.example.ui.theme.GlassAccentTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SettingsUiState(
    val allSettings: List<SettingItem> = emptyList(),
    val searchQuery: String = "",
    val selectedLetter: Char? = null,
    val selectedCategoryFilter: String? = null,
    val toggleStates: Map<String, Boolean> = emptyMap(),
    val accentTheme: GlassAccentTheme = GlassAccentTheme.CYBER_CYAN,
    val enableShimmer: Boolean = true,
    val glassOpacity: Float = 0.16f,
    val selectedItemForDetail: SettingItem? = null,
    val showProfileSheet: Boolean = false,
    val showQRScanner: Boolean = false
) {
    val filteredSettings: List<SettingItem>
        get() {
            return allSettings.filter { item ->
                val matchesQuery = if (searchQuery.isBlank()) {
                    true
                } else {
                    item.title.contains(searchQuery, ignoreCase = true) ||
                            item.subtitle.contains(searchQuery, ignoreCase = true) ||
                            item.category.contains(searchQuery, ignoreCase = true) ||
                            item.description.contains(searchQuery, ignoreCase = true)
                }

                val matchesLetter = if (selectedLetter == null) {
                    true
                } else {
                    item.letter.equals(selectedLetter, ignoreCase = true)
                }

                val matchesCategory = if (selectedCategoryFilter == null) {
                    true
                } else {
                    item.category.contains(selectedCategoryFilter, ignoreCase = true)
                }

                matchesQuery && matchesLetter && matchesCategory
            }
        }

    val groupedByLetter: Map<Char, List<SettingItem>>
        get() = filteredSettings.groupBy { it.letter }.toSortedMap()

    val availableLetters: Set<Char>
        get() = allSettings.map { it.letter }.toSet()
}

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        val settings = SettingsRepository.getAllSettings()
        val initialToggles = settings
            .filter { it.hasToggle }
            .associate { it.id to it.defaultEnabled }

        _uiState.update {
            it.copy(
                allSettings = settings,
                toggleStates = initialToggles
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onLetterSelected(letter: Char?) {
        _uiState.update { it.copy(selectedLetter = letter) }
    }

    fun onCategoryFilterSelected(category: String?) {
        _uiState.update {
            it.copy(
                selectedCategoryFilter = if (it.selectedCategoryFilter == category) null else category
            )
        }
    }

    fun onToggleSetting(id: String, enabled: Boolean) {
        _uiState.update { current ->
            val updated = current.toggleStates.toMutableMap()
            updated[id] = enabled
            current.copy(toggleStates = updated)
        }
    }

    fun onSelectTheme(theme: GlassAccentTheme) {
        _uiState.update { it.copy(accentTheme = theme) }
    }

    fun onToggleShimmer(enabled: Boolean) {
        _uiState.update { it.copy(enableShimmer = enabled) }
    }

    fun onGlassOpacityChanged(opacity: Float) {
        _uiState.update { it.copy(glassOpacity = opacity) }
    }

    fun onOpenDetail(item: SettingItem) {
        if (item.id == "q_qr_scanner") {
            _uiState.update { it.copy(showQRScanner = true) }
        } else {
            _uiState.update { it.copy(selectedItemForDetail = item) }
        }
    }

    fun onDismissDetail() {
        _uiState.update { it.copy(selectedItemForDetail = null) }
    }

    fun onOpenProfile() {
        _uiState.update { it.copy(showProfileSheet = true) }
    }

    fun onDismissProfile() {
        _uiState.update { it.copy(showProfileSheet = false) }
    }

    fun onDismissQRScanner() {
        _uiState.update { it.copy(showQRScanner = false) }
    }

    fun onGlanceCardClicked(itemId: String) {
        val item = _uiState.value.allSettings.find { it.id == itemId }
        if (item != null) {
            onOpenDetail(item)
        }
    }
}
