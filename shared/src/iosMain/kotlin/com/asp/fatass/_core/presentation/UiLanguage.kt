package com.asp.fatass._core.presentation

import com.asp.fatass._core.domain.util.Language

actual class UiLanguage(
    actual val language: Language,
    val imageName: String
) {
    actual companion object {
        actual fun byCode(langCode: String): UiLanguage? {
            return allLanguages.find { it.language.langCode == langCode }
        }

        actual val allLanguages: List<UiLanguage>
            get() = Language.values().map { language ->
                UiLanguage(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }

        actual val localizedLanguages: List<UiLanguage>
            get() = allLanguages.filter { Language.localizedLanguages.contains(it.language) }
    }
}