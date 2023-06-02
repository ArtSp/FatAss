package com.asp.fatass.core.presentation

import com.asp.fatass.core.domain.util.CommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AppViewModel<State, Event>(
    private val coroutineScope: CoroutineScope?
) {
    val viewModelScope: CoroutineScope by lazy {
        coroutineScope ?: CoroutineScope(Dispatchers.Main)
    }

    abstract val state: CommonStateFlow<State>
    abstract fun onEvent(event: Event)
}