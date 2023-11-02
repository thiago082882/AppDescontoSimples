package com.thiago.appdescontosimples.model

data class CalcularState(
    val preco : String = "",
val desconto : String = "",
    val precoDesconto : Double = 0.0,
    val totalDesconto : Double = 0.0,
    val showAlert : Boolean = false
)
