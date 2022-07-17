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
import views.BackdropScaffoldLayout

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowPage() {
    val scope = rememberCoroutineScope()
    val selection = remember { mutableStateOf(0) }
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

    BackdropScaffoldLayout(
        scaffoldState = scaffoldState,
        appBar = {},
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
        },) {
        Column {
            Text("Selection: ${selection.value}", modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp))
            LazyColumn {
                items(22) {
                    ListItem(
                        text = { Text("Selection: ${selection.value} =>Item $it") },
                        secondaryText = {
                            Text("secondaryText: ${selection.value} =>Item $it")
                        },
                        overlineText = {
                            Text("overlineText: ${selection.value} =>Item $it")
                        },
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
}
