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
import com.thiago.appdescontosimples.viewModels.CalcularViewModel1
import com.thiago.appdescontosimples.viewModels.CalcularViewModel2
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView2(viewModel2 : CalcularViewModel2) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "App Desconto", color = Color.White) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        HomeViewContent2(it,viewModel2)
    }
}

@Composable
fun HomeViewContent2(paddingValues: PaddingValues,viewModel2: CalcularViewModel2) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TwoCards(
            title1 = "total",
            number1 = viewModel2.totalDesconto,
            title2 = "Desconto",
            number2 = viewModel2.precoDesconto
        )
        MainTextField(
            value = viewModel2.preco,
            onValueChange = { viewModel2.onValue(it,"preco") },
            label = "Preço"
        )
        SpaceH()
        MainTextField(
            value = viewModel2.desconto,
            onValueChange = { viewModel2.onValue(it,"desconto") },
            label = "Desconto %"
        )
        SpaceH(10.dp)
        MainButton(text = "Gerar Desconto") {

 viewModel2.calcular()

        }
        SpaceH()
        MainButton(text = "Limpar", color = Color.Red) {
  viewModel2.limpar()

        }

        if (viewModel2.showAlert){
            Alert(
                title = "Alerta",
                message = "Escreva o Preço e desconto",
                confirmText = "Aceitar",
                onConfirmClick = {viewModel2.calcelAlert() }) {

            }
        }


    }
}

