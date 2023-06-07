package com.asp.fatass._core.presentation

import com.asp.fatass._core.domain.util.flow.CommonStateFlow
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