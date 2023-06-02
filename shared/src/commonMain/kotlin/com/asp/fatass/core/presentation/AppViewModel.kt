package com.asp.fatass.core.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class AppViewModel<State, Event>(
    private val coroutineScope: CoroutineScope?
) {
    val viewModelScope: CoroutineScope by lazy {
        coroutineScope ?: CoroutineScope(Dispatchers.Main)
    }

    abstract fun onEvent(event: Event)
}