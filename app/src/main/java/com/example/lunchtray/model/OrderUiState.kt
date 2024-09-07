package com.example.lunchtray.model

import com.example.lunchtray.data.DataSource

data class OrderUiState(
    val entree: MenuItem = DataSource.entreeMenuItems[0],
    val sideDish: MenuItem = DataSource.sideDishMenuItems[0],
    val accompaniment: MenuItem = DataSource.accompanimentMenuItems[0],
    val itemTotalPrice: Double = 0.0,
    val orderTax: Double = 0.0,
    val orderTotalPrice: Double = 0.0
)