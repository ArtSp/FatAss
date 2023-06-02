package com.asp.fatass.sections.presentation

import com.asp.fatass.core.domain.util.error.AppError
import com.asp.fatass.sections.domain.Section

data class SectionsState (
    val isChoosingLanguage: Boolean = false,
    val error: AppError? = null,
    val sections: List<Section>? = null,
    val selectedSection: Section? = null,
    val isLoading: Set<Content> = setOf()
) {
    enum class Content {
        SECTIONS
    }
}