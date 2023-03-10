package com.example.testscroll

import retrofit2.Response

class UiState {

    sealed class UiEvent {
        object LoadMore : UiEvent()
        object Complete : UiEvent()
        object Progress : UiEvent()
        data class Error(val errorData: Response<*>) : UiEvent()
    }
}
