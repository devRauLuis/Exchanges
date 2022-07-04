package com.devaruluis.exchanges.ui.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devaruluis.exchanges.ui.viewmodel.CoinsViewModel

@Composable
fun CoinRegistryBody(
    viewModel: CoinsViewModel = hiltViewModel(),
    id: String? = ""
) {
    var uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    if (uiState.showSnackbar) {
        LaunchedEffect(uiState.showSnackbar) {
            try {
                when (snackbarHostState.showSnackbar(
                    uiState.snackbarMessage.toString(),
                )) {
                    SnackbarResult.Dismissed -> {
                    }
                    else -> {}
                }
            } finally {
                viewModel.dismissSnackbar()
            }
        }
    }

    LaunchedEffect(id) {
        println("id arg: $id")

        if (!id.isNullOrEmpty()) {
            viewModel.setId(id)
            viewModel.find()
        }
    }


    val textFieldModifier = Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 50.dp)
        .padding(top = 10.dp)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column() {
            Text(text = "Registro", fontSize = 24.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {

//                IdSearchField(
//                    value = uiState.id,
//                    handleValueChange = { viewModel.setId(it) },
//            r        handleSearchClick = { viewModel.find() })


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = uiState.id ?: "",
                        onValueChange = {
                            viewModel.setId(it)
                        },
                        label = { Text(text = "ID") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Fingerprint,
                                contentDescription = "ID"
                            )
                        },

                        modifier = Modifier
                            .fillMaxWidth(.8f)
                            .defaultMinSize(minHeight = 50.dp),
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(.1f))
                    Button(
                        onClick = { viewModel.find() },
                        modifier = Modifier
                            .defaultMinSize(minHeight = 50.dp)
                            .fillMaxWidth(),
                    ) {
                        Icon(Icons.Filled.Search, "Search")
                    }
                }

                TextField(
                    value = uiState.name ?: "",
                    onValueChange = {
                        viewModel.setName(it)
                    },
                    label = { Text(text = "Nombre") },
                    modifier = textFieldModifier, leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Nombre"
                        )
                    }
                )

                TextField(
                    value = if (uiState.price != null) uiState.price.toString() else 0F.toString(),
                    onValueChange = {
                        viewModel.setPrice(it.toFloatOrNull())
                    },
                    label = { Text(text = "Precio") },
                    modifier = textFieldModifier,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Money,
                            contentDescription = "Precio"
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    )

                TextField(
                    value = uiState.img ?: "",
                    onValueChange = {
                        viewModel.setImg(it)
                    },
                    label = { Text(text = "Imagen") },
                    modifier = textFieldModifier, leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Image,
                            contentDescription = "Imagen"
                        )
                    }
                )

                TextField(
                    value = uiState.symbol ?: "",
                    onValueChange = {
                        viewModel.setSymbol(it)
                    },
                    label = { Text(text = "Simbolo") },
                    modifier = textFieldModifier, leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.EmojiSymbols,
                            contentDescription = "Simbolo"
                        )
                    }
                )

                TextField(
                    value = if (uiState.rank != null) uiState.rank.toString() else 0.toString(),
                    onValueChange = {
                        viewModel.setRank(it.toIntOrNull() ?: 0)
                    },
                    label = { Text(text = "Rank") },
                    modifier = textFieldModifier,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rank"
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.isNew ?: false,
                            onCheckedChange = { viewModel.setIsNew(it) })
                        Spacer(modifier = Modifier.size(5.dp))
                        Text("Es nuevo")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = uiState.isActive ?: false,
                            onCheckedChange = { viewModel.setIsActive(it) })
                        Spacer(modifier = Modifier.size(5.dp))
                        Text("Esta activo")
                    }
                }
                TextField(
                    value = uiState.type.toString(),
                    onValueChange = {
                        viewModel.setType(it)
                    },
                    label = { Text(text = "Tipo") },
                    modifier = textFieldModifier, leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Tipo"
                        )
                    }
                )

                SnackbarHost(hostState = snackbarHostState)
                val buttonModifier = Modifier.defaultMinSize(minWidth = 80.dp)
                Card(modifier = Modifier.fillMaxWidth()) {

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            viewModel.new()
                        },
                        modifier = buttonModifier
                    ) {
                        Text(text = "NUEVO")
                    }
                    Button(
                        onClick = { viewModel.save() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        modifier = buttonModifier

                    ) {
                        Text(text = "GUARDAR")
                    }
                    OutlinedButton(
                        onClick = { viewModel.delete() },
                        modifier = buttonModifier
                    ) {
                        Text(text = "ELIMINAR")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistryBodyPreview() {
    Surface {
        CoinRegistryBody()
    }
}