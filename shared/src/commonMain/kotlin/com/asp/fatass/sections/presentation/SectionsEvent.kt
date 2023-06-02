package com.asp.fatass.sections.presentation

import com.asp.fatass.core.presentation.UiLanguage
import com.asp.fatass.sections.domain.Section

sealed class SectionsEvent {
    object OpenLanguageDropDown: SectionsEvent()
    object CloseLanguageDropDown: SectionsEvent()
    data class ChooseLanguage(val language: UiLanguage): SectionsEvent()
    data class ChooseSection(val section: Section): SectionsEvent()
}