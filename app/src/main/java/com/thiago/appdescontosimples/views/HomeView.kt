package com.thiago.appdescontosimples.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thiago.appdescontosimples.components.Alert
import com.thiago.appdescontosimples.components.MainButton
import com.thiago.appdescontosimples.components.MainTextField
import com.thiago.appdescontosimples.components.SpaceH
import com.thiago.appdescontosimples.components.TwoCards
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "App Desconto", color = Color.White) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        HomeViewContent(it)
    }
}

@Composable
fun HomeViewContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var preco by remember {
            mutableStateOf("")
        }
        var desconto by remember {
            mutableStateOf("")
        }
        var precoDesconto by remember {
            mutableStateOf(0.0)
        }
        var totalDesconto by remember {
            mutableStateOf(0.0)
        }

        var showAlert by remember {
            mutableStateOf(false)
        }
        TwoCards(
            title1 = "total",
            number1 = totalDesconto,
            title2 = "Desconto",
            number2 = precoDesconto
        )
        MainTextField(
            value = preco,
            onValueChange = { preco = it },
            label = "Preço"
        )
        SpaceH()
        MainTextField(
            value = desconto,
            onValueChange = { desconto = it },
            label = "Desconto %"
        )
        SpaceH(10.dp)
        MainButton(text = "Gerar Desconto") {
       if (preco != "" && desconto != ""){
           precoDesconto = calcularPreco(preco.toDouble(),desconto.toDouble())
           totalDesconto = calcularDesconto(preco.toDouble(),desconto.toDouble())

       }else{
            showAlert = true
       }

    }
        SpaceH()
        MainButton(text = "Limpar", color = Color.Red) {

            preco = ""
            desconto = ""
            precoDesconto = 0.0
            totalDesconto = 0.0

        }

        if (showAlert){
            Alert(
                title = "Alerta",
                message = "Escreva o Preço e desconto",
                confirmText = "Aceitar",
                onConfirmClick = { showAlert = false }) {

            }
        }


    }
}

fun calcularPreco(preco : Double,desconto:Double):Double{

    val res = preco - calcularDesconto(preco,desconto)
    return  round(res * 100) / 100.0

}

fun calcularDesconto(preco : Double,desconto:Double):Double{
    val res = preco * (1 - desconto / 100)
    return  round(res * 100) / 100.0

}