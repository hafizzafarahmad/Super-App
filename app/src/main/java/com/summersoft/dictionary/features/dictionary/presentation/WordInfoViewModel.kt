package com.summersoft.dictionary.features.dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.summersoft.dictionary.core.utils.Resource
import com.summersoft.dictionary.features.dictionary.domain.use_case.GetWordInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUsecase: GetWordInfoUsecase
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(word: String){
        _searchQuery.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfoUsecase(word = word).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(
                            message = result.message ?: "Unknown Error"
                        ))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message:String): UIEvent()
    }
}