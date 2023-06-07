package com.asp.fatass._core.presentation

import com.asp.fatass._core.domain.util.Language

expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UiLanguage?
        val allLanguages: List<UiLanguage>
        val localizedLanguages: List<UiLanguage>
    }
}