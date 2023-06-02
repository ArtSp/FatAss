package com.asp.fatass.categories.presentation

import com.asp.fatass.core.domain.util.toCommonStateFlow
import com.asp.fatass.core.presentation.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CategoriesViewModel(
    coroutineScope: CoroutineScope?
): AppViewModel<CategoriesState, CategoriesEvent>(coroutineScope) {

    private var getSectionsJob: Job? = null

    private val _state = MutableStateFlow(CategoriesState())
    override val state = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoriesState())
        .toCommonStateFlow()

    override fun onEvent(
        event: CategoriesEvent
    ) {
        when (event) {
            is CategoriesEvent.OpenLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingLanguage = true
                    )
                }
            }

            is CategoriesEvent.CloseLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingLanguage = false
                    )
                }
            }

            is CategoriesEvent.ChooseLanguage -> {
                TODO("Handle selected language ${event.language.language.langName}")
            }

            is CategoriesEvent.ChooseSection -> {
                _state.update {
                    it.copy(
                        selectedCategory = event.section
                    )
                }
            }
        }
    }

    private fun getSections(
        state: CategoriesState
    ) {

    }
}