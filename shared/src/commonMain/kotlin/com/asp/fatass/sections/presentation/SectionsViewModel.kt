package com.asp.fatass.sections.presentation

import com.asp.fatass.core.domain.util.toCommonStateFlow
import com.asp.fatass.core.presentation.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SectionsViewModel(
    coroutineScope: CoroutineScope?
): AppViewModel<SectionsState, SectionsEvent>(coroutineScope) {

    private val _state = MutableStateFlow(SectionsState())
    val state = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SectionsState())
        .toCommonStateFlow()

    private var getSectionsJob: Job? = null

    override fun onEvent(
        event: SectionsEvent
    ) {
        when (event) {
            is SectionsEvent.OpenLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingLanguage = true
                    )
                }
            }

            is SectionsEvent.CloseLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingLanguage = false
                    )
                }
            }

            is SectionsEvent.ChooseLanguage -> {
                TODO("Handle selected language ${event.language.language.langName}")
            }

            is SectionsEvent.ChooseSection -> {
                _state.update {
                    it.copy(
                        selectedSection = event.section
                    )
                }
            }
        }
    }

    private fun getSections(
        state: SectionsState
    ) {

    }
}