package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowPage() {
    val scope = rememberCoroutineScope()
    val selection = remember { mutableStateOf(0) }
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    LaunchedEffect(scaffoldState) {
        scaffoldState.conceal()
    }
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {

        },
        /*  appBar = {
              TopAppBar(
                  title = { Text("Backdrop scaffold") },
                  navigationIcon = {
                      if (scaffoldState.isConcealed) {
                          IconButton(onClick = { scope.launch { scaffoldState.reveal() } }) {
                              Icon(Icons.Default.Menu, contentDescription = "Localized description")
                          }
                      } else {
                          IconButton(onClick = { scope.launch { scaffoldState.conceal() } }) {
                              Icon(Icons.Default.Close, contentDescription = "Localized description")
                          }
                      }
                  },
                  actions = {
                      var clickCount by remember { mutableStateOf(0) }
                      IconButton(
                          onClick = {
                              // show snackbar as a suspend function
                              scope.launch {
                                  scaffoldState.snackbarHostState
                                      .showSnackbar("Snackbar #${++clickCount}")
                              }
                          }
                      ) {
                          Icon(Icons.Default.Favorite, contentDescription = "Localized description")
                      }
                  },
                  elevation = 0.dp,
                  backgroundColor = Color.Transparent
              )
          },*/
        backLayerContent = {
            LazyColumn {
                items(if (selection.value >= 3) 3 else 5) {
                    ListItem(
                        Modifier.clickable {
                            selection.value = it
                            scope.launch { scaffoldState.conceal() }
                        },
                        text = { Text("Select $it") }
                    )
                }
            }
        },
        frontLayerContent = {
            Column {
                Text("Selection: ${selection.value}", modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp))
                LazyColumn {
                    items(50) {
                        ListItem(
                            text = { Text("Selection: ${selection.value} =>Item $it") },
                            icon = {
                                Icon(
                                    Icons.Default.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                    }
                }
            }
        }
    )
}
