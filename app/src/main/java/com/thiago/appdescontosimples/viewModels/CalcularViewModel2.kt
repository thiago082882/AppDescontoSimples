package com.thiago.appdescontosimples.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.round

class CalcularViewModel2 : ViewModel() {

    var preco by mutableStateOf("")
     private set

//    fun onValuePreco(value:String){
//        preco = value
//
//    }
//    fun onValueDesconto(value:String){
//        desconto = value
//
//    }

    var desconto by mutableStateOf("")
        private set

fun onValue(value:String,text:String){
    when(text){
        "preco" -> preco = value
        "desconto" -> desconto = value
    }

}

    var precoDesconto by mutableStateOf(0.0)
        private set

    var totalDesconto by mutableStateOf(0.0)
        private set
    var showAlert by mutableStateOf(false)
        private set

    fun calcular(){

        if (preco != "" && desconto != ""){
            precoDesconto = calcularPreco(preco.toDouble(),desconto.toDouble())
            totalDesconto = calcularDesconto(preco.toDouble(),desconto.toDouble())

        }else{
            showAlert = true
        }

    }
    private fun calcularPreco(preco : Double,desconto:Double):Double{

        val res = preco - calcularDesconto(preco,desconto)
        return  round(res * 100) / 100.0

    }

    private fun calcularDesconto(preco : Double,desconto:Double):Double{
        val res = preco * (1 - desconto / 100)
        return  round(res * 100) / 100.0

    }

    fun limpar(){
        preco = ""
        desconto= ""
        precoDesconto = 0.0
        totalDesconto = 0.0
    }

    fun calcelAlert(){
        showAlert = false
    }
}