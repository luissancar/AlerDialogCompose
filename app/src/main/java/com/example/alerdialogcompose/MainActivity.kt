package com.example.alerdialogcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.alerdialogcompose.ui.theme.AlerDialogComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlerDialogComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   Screen()

                }
            }
        }
    }
}


@Composable
fun Screen() {


    var lista : MutableList<String> = remember {
        mutableStateListOf("a","b","c")

    }
    
    val openDialog = remember { mutableStateOf(false)  }

    var textFieldValue by rememberSaveable { mutableStateOf("") }
    var ventana=AlertDi(openDialog = openDialog)


    Column() {

        Button(onClick = {
            openDialog.value=true
            lista.add(ventana)
        }) {
        Text(text = "Pulsar")
        }




        LazyColumn {
            items(lista){
                elemento ->
            Text(text = elemento)

            }
        }
    }
    
}




@Composable
fun AlertDi(openDialog: MutableState<Boolean>): String{

    var textFieldValue by rememberSaveable { mutableStateOf("") }

    if (openDialog.value)
    {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "AlertDialog")},
            text = {

                TextField(
                    value = textFieldValue,
                    onValueChange = {nuevoDato ->
                        textFieldValue= nuevoDato
                    },
                    label = {
                        Text(text = "Introducir dato")
                    },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(textAlign = TextAlign.Right),
                    leadingIcon = {
                        // In this method we are specifying
                        // our leading icon and its color.
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "image",
                            tint = Color.Blue
                        )
                    }

                )
                   },


            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false

                    }) {
                        Text(text = "Confirm")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false


                        textFieldValue="Cancel"

                    }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
    return textFieldValue
}