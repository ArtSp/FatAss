package com.asp.fatass.categories.presentation

import com.asp.fatass.core.domain.products.ProductsDataSource
import com.asp.fatass.core.domain.products.ProductsUseCase
import com.asp.fatass.core.domain.util.Resource
import com.asp.fatass.core.domain.util.error.AppException
import com.asp.fatass.core.domain.util.toCommonStateFlow
import com.asp.fatass.core.presentation.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val productsUseCase: ProductsUseCase,
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
                        isChoosingLanguage = event.value
                    )
                }
            }

            is CategoriesEvent.ChooseLanguage -> {
                TODO("Handle selected language ${event.language.language.langName}")
            }

            is CategoriesEvent.ChooseCategory -> {
                _state.update {
                    it.copy(
                        selectedCategory = event.category
                    )
                }
            }

            is CategoriesEvent.LoadContent -> {
                getSections(state.value)
            }

            is CategoriesEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }

    private fun getSections(
        state: CategoriesState
    ) {
        getSectionsJob?.cancel()
        getSectionsJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = it.isLoading.plus(CategoriesState.Content.CATEGORIES)
                )
            }

            val result = productsUseCase.getCategories()

            when(result) {
                is Resource.Success -> {
                    _state.update { it.copy(
                        categories = result.data,
                        isLoading = it.isLoading.minus(CategoriesState.Content.CATEGORIES),
                        error = null
                    ) }
                }
                is Resource.Error -> {
                    _state.update { it.copy(
                        isLoading = it.isLoading.minus(CategoriesState.Content.CATEGORIES),
                        error = (result.throwable as? AppException)?.error
                    ) }
                }
            }
        }
    }
}