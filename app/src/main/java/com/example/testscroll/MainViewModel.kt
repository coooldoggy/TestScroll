package com.example.testscroll

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var songListState by mutableStateOf<List<Song>>(emptyList())
    var uiState by mutableStateOf<UiState.UiEvent>(UiState.UiEvent.Progress)
    private val _uiState: MutableSharedFlow<UiState.UiEvent> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.collect {
                handleUiEvent(it)
            }
        }
    }

    private fun enableMoreToLoad(enableMoreToLoad: Boolean) {
        loadDataComplete(enableMoreToLoad)
    }

    private fun loadDataComplete(enableMoreToLoad: Boolean = false) {
        uiState = if (enableMoreToLoad) {
            UiState.UiEvent.Progress
        } else {
            UiState.UiEvent.Complete
        }
    }

    private fun handleUiEvent(event: UiState.UiEvent) {
        when (event) {
            is UiState.UiEvent.LoadMore -> {
                loadData()
            }
            is UiState.UiEvent.Complete -> {
                uiState = UiState.UiEvent.Complete
            }
            is UiState.UiEvent.Error -> {
            }
            is UiState.UiEvent.Progress -> {
                uiState = UiState.UiEvent.Progress
            }
        }
    }

    fun loadData() {
        if (songListState.isEmpty()) {
            viewModelScope.launch {
                val result = ApiManager.queryDaySongs(0)
                if (result.isSuccessful) {
                    songListState = result.body()?.results ?: emptyList()
                    enableMoreToLoad(true)
                }
            }
        } else {
            viewModelScope.launch {
                val result = ApiManager.queryDaySongs(songListState.size)
                if (result.isSuccessful) {
                    val songList: MutableList<Song> = ArrayList()
                    songList.addAll(songListState)
                    songList.addAll(result.body()?.results ?: emptyList())
                    songListState = songList
                    enableMoreToLoad(true)
                }
            }
        }
    }
}
