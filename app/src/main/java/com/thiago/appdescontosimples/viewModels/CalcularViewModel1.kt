package com.thiago.appdescontosimples.viewModels

import androidx.lifecycle.ViewModel
import kotlin.math.round

class CalcularViewModel1 : ViewModel() {

    fun calcular(preco:String,desconto:String):Pair<Double,Pair<Double,Boolean>>{
      var precoDesconto = 0.0
      var totalDesconto = 0.0
        var showAlert = false
        if (preco != "" && desconto != ""){
            precoDesconto = calcularPreco(preco.toDouble(),desconto.toDouble())
            totalDesconto = calcularDesconto(preco.toDouble(),desconto.toDouble())

        }else{
            showAlert = true
        }
        return  Pair(precoDesconto,Pair(totalDesconto,showAlert))
    }
   private fun calcularPreco(preco : Double,desconto:Double):Double{

        val res = preco - calcularDesconto(preco,desconto)
        return  round(res * 100) / 100.0

    }

   private fun calcularDesconto(preco : Double,desconto:Double):Double{
        val res = preco * (1 - desconto / 100)
        return  round(res * 100) / 100.0

    }
}