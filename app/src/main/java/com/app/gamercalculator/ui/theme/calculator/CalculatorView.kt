package com.app.gamercalculator.ui.theme.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.gamercalculator.R

@Preview
@Composable
fun CalculatorView() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
        //.background(Color.Red)
    ) {
        Calculator(Modifier.align(Alignment.TopStart))
    }


}

@Composable
fun Calculator(modifier: Modifier) {
    Column(modifier = modifier) {
        //Spacer(modifier = Modifier.padding(90.dp))
        Spinner()
        Spacer(modifier = Modifier.padding(18.dp))
        TextFieldCalculate()
        Spacer(modifier = Modifier.padding(18.dp))
        TextTest()
        Spacer(modifier = Modifier.padding(18.dp))
        TextTest2()
    }
}

//@Preview(showBackground = true)
@Composable
fun TextTest() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 90.dp),
        text = "Calculadora",
        fontWeight = FontWeight.Bold,
        color = androidx.compose.ui.graphics.Color.Red,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun TextTest2() {
    Text(
        text = "Calculadora",
        fontWeight = FontWeight.Bold,
        color = androidx.compose.ui.graphics.Color.Red,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun Spinner() {
    val listPlataforms = listOf("Tienda en dolares", "Tienda en pesos", "Suscripciones")
    val expanded = remember { mutableStateOf(false) }
    val currentValue = remember { mutableStateOf(listPlataforms[0]) }

    // Surface(modifier = Modifier.fillMaxSize()) { //Esto crea una superficie que ocupa todo el espacio disponible en la pantalla.
    //}
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {// Define un contenedor tipo caja que ocupa todo el ancho disponible en el espacio de la superficie.
        Row(modifier = Modifier
            .clickable {
                expanded.value = !expanded.value
            }
            .align(Alignment.Center)) {
            Text(text = currentValue.value)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            DropdownMenu(expanded = expanded.value, onDismissRequest = {
                expanded.value = false
            }) {

                listPlataforms.forEach {

                    DropdownMenuItem(onClick = {
                        currentValue.value = it
                        expanded.value = false
                    }) {
                        Text(text = it)
                    }

                }

            }
        }

    }
}

@Composable
@Preview
fun TextFieldCalculate() {
    val txtCalculate by remember { mutableStateOf("") }
    TextField(
        value = "", onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "$0") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        maxLines = 30,
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.black),
            focusedIndicatorColor = Color.Transparent, unfocusedLabelColor = Color.Transparent
        )

    )
}

