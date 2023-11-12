package com.example.tugasucp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tugasucp.data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class OrderViewModel : ViewModel() {
    private  val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()
    var pilihBh: String by mutableStateOf("")
        private set

    var pilihKt: String by mutableStateOf("")
        private set


    fun insertDataBuah(bh:String){
        pilihBh = bh;
    }

    fun insertDataKota(kt:String){
        pilihKt = kt;
    }

    fun setBuah(pilihBh:String){
        _stateUI.update { currentState -> currentState.copy(fruit = pilihBh) }
    }

    fun setKota(pilihKt:String){
        _stateUI.update { currentState -> currentState.copy(city = pilihKt) }
    }

    fun resetOrder() {
        _stateUI.value = OrderUIState()
    }
}