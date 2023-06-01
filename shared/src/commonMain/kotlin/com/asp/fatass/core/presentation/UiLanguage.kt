package com.asp.fatass.core.presentation

import com.asp.fatass.core.domain.Language

expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}