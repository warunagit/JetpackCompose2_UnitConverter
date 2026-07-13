package com.example.jetpack2_unitconverter

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UnitApp(modifier: Modifier = Modifier){
    //map of unit types
    var conversionRate = mapOf(
        "Metre" to 1.0,
        "Centimetre" to 100.0,
        "Feet" to 3.2804,
        "Inches" to 39.3701
    )
    //default base unit type
    var fromUnit = remember {
        mutableStateOf("Metre")
    }
    var toUnit = remember {
        mutableStateOf("Centimetre")
    }

    //store values
    var inputValue = remember {
        mutableStateOf("")
    }
    var outputValue = remember {
        mutableStateOf("")
    }

    //store expand state value
    var expandFrom = remember {
        mutableStateOf(false)
    }
    var expandTo = remember {
        mutableStateOf(false)
    }

    //Generate ui
    Column(
        //column-turn all elements in to columns
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow.copy(alpha = .6f))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                /*.border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium
                )*/
        ){
            Text(
                text="Unit Converter",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row(
            //ui layout
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement
                .SpaceEvenly
        ){
            //1st element wrapper
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clickable{
                        expandFrom.value = true
                    }.border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.medium
                    ).padding(start = 10.dp)
            ){
                //1st ui element
                Text(
                    text = fromUnit.value,
                    modifier = Modifier.align(Alignment.Center)
                )
                //2nd ui element
                DropdownMenu(
                    expandFrom.value,
                    onDismissRequest = {expandFrom.value = false}
                ) {

                    conversionRate.keys.forEach {
                        DropdownMenuItem(
                            text = {Text(text = it)},
                            onClick = {
                                fromUnit.value = it
                                expandFrom.value = false
                            }
                        )
                    }
                }
            }
            //2nd element: spacer
            Spacer(modifier = Modifier.width(16.dp))

            //3rd element wrapper
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clickable{
                        expandTo.value = true
                    }.border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.medium
                    )
            ){
                Text(
                    text = toUnit.value,
                    modifier = Modifier.align(Alignment.Center)
                )
                DropdownMenu(
                    expandTo.value,
                    onDismissRequest = {expandTo.value = false}
                ) {

                    conversionRate.keys.forEach {
                        DropdownMenuItem(
                            text = {Text(text = it)},
                            onClick = {
                                fromUnit.value = it
                                expandTo.value = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
            },
            label = {
                Text(text = "Enter value")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}