import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import models.TabModels
import ui.Ui_Main
import kotlin.reflect.KClass
import kotlin.reflect.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = lightColors(primary = Color.Black)
    ) {
        Ui_Main()
        Test()
    }
}

fun main() = application {
    val windowState = rememberWindowState()
    val dpSize : DpSize  = DpSize(Window_Width, Window_Heigth)
    windowState.size = dpSize
    Window(
        onCloseRequest = ::exitApplication,
        title = AppTilte,
        icon = painterResource(AppIcon),
        resizable = false,
        state = windowState
    ) {
        App()
    }
}

fun Test(){
    println("Test")
    val testClass = Class.forName("models.TabModels").kotlin

    var test = testClass.createInstance()
    testClass.declaredFunctions.forEach{
        println("test 方法：${it.name}")
    }
}
