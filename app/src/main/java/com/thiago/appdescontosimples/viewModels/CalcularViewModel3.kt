package com.thiago.appdescontosimples.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.thiago.appdescontosimples.model.CalcularState
import kotlin.math.round

class CalcularViewModel3 : ViewModel() {

    var state by mutableStateOf(CalcularState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "preco" -> state = state.copy(preco = value)
            "desconto" -> state = state.copy(desconto = value)
        }
    }

    fun calcular() {
        val preco = state.preco
        val desconto = state.desconto
        state = if (preco != "" && desconto != "") {
            state.copy(
                precoDesconto = calcularPreco(preco.toDouble(), desconto.toDouble()),
                totalDesconto = calcularDesconto(preco.toDouble(), desconto.toDouble())
            )

        } else {
            state.copy(
                showAlert = true
            )
        }

    }

    private fun calcularPreco(preco: Double, desconto: Double): Double {

        val res = preco - calcularDesconto(preco, desconto)
        return round(res * 100) / 100.0

    }

    private fun calcularDesconto(preco: Double, desconto: Double): Double {
        val res = preco * (1 - desconto / 100)
        return round(res * 100) / 100.0

    }

    fun limpar() {
        state = state.copy(
            preco = "",
            desconto = "",
            precoDesconto = 0.0,
            totalDesconto = 0.0
        )
    }

    fun calcelAlert() {
        state = state.copy(showAlert = false)
    }
}