package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import com.ll.lib.utils.FileUtils
import views.DialogView

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
                UI_Main_FloatButton {
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
                ShowPage()
            }
            DialogView(
                title = "弹窗",
                text = "弹窗描述文本",
                openDialog = openDialog,
                onDismissRequestCallback = { openDialog = it }) {
                openDialog = it
                println("==this project path is ${FileUtils.PROJECT_PATH}")
                println("==this project config path is ${FileUtils.PROJECT_CONFIG_PATH}")
            }
        }
    }
}
