package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*

@Composable
fun Ui_Main() {
    var openDialog by remember { mutableStateOf(false) }
    Column {
        Scaffold(
            topBar = {
                UI_Main_TopBar()
            },
            bottomBar = {
                UI_Main_BottomBar()
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                UI_Main_FloatButton{
                    println("UI_Main_FloatButton onClick:")
                    openDialog = it
                }
            },
            drawerContent = {
                Column {
                    DrawerMenuContent()
                }
            }
        ) {
            Column {
               /* Button(onClick = {
                    text = "Hello, Desktop!"
                }) {
                    Text(text)
                }*/
                ShowPage()
            }
            UI_Main_Dialog(openDialog, { openDialog = it}){
                openDialog = it
            }
        }
    }
}
