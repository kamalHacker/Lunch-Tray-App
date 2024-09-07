package com.example.lunchtray

import androidx.lifecycle.ViewModel
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val taxRate = 0.089

    fun updateEntree(entreeItem: MenuItem) {
        _uiState.update { currentState ->
            currentState.copy(entree = entreeItem)
        }
    }

    fun updateSideDish(sideDishItem: MenuItem) {
        _uiState.update { currentState ->
            currentState.copy(sideDish = sideDishItem)
        }
    }

    fun updateAccompaniment(accompanimentItem: MenuItem) {
        _uiState.update { currentState ->
            currentState.copy(accompaniment = accompanimentItem)
        }
    }

    fun updatePrice(
        entree: MenuItem = uiState.value.entree,
        sideDish: MenuItem = uiState.value.sideDish,
        accompaniment: MenuItem = uiState.value.accompaniment
    ) {
        val entreePrice = entree.price
        val sideDishPrice = sideDish.price
        val accompanimentPrice = accompaniment.price

        val itemTotalPrice = entreePrice + sideDishPrice + accompanimentPrice
        val orderTax = itemTotalPrice * taxRate
        val orderTotalPrice = itemTotalPrice + orderTax

        _uiState.update { currentState ->
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = orderTax,
                orderTotalPrice = orderTotalPrice
            )
       }
    }

    fun resetOrder(){
        _uiState.value = OrderUiState()
    }

}