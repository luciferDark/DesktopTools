import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import models.TabModels
import ui.Ui_Main
import java.io.File
import kotlin.reflect.KClass
import kotlin.reflect.*
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.functions

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = lightColors(primary = Color.Black)
    ) {
        Ui_Main()
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
//    val testClass = TabModels::class

    var test = testClass.createInstance()
    testClass.declaredFunctions.forEach{
        println("test 方法：${it.name}")
    }
    var functionInvoke = testClass.functions.find{ it.name == "printStr"}
    functionInvoke?.call(test, "call function")

    var classLoaderMain = Thread.currentThread().contextClassLoader
    println("classLoaderMain name：${classLoaderMain.name}")
    classLoaderMain.definedPackages.forEach {
//        println("classLoaderMain package name：${it.name}")
    }
    classLoaderMain.getDefinedPackage(".")
    for (resource in classLoaderMain.getResources(".")) {
        println("classLoaderMain resource file：${resource.file} and Protocol：${resource.protocol} and host：${resource.host} and path：${resource.path}")
    }

    var classPaths = classLoaderMain.getResources(".").toList().filter { it.path.contains("/build/classes")  }.map {
        if (it.path.startsWith("/")){
            it.path.substring(1, it.path.length)
        } else {
            it.path
        }
    }

    classPaths.forEach {
        println("classLoaderMain path：$it")
        findAllClassesInDir(it)
    }
}
fun findAllClassesInDir(path : String,
                        fileMap: ((File) -> File)? = null ,
                        fileFilter: ((File)->Boolean)? = null) : MutableList<KClass<Any>>? {
    path?:return null
    var result : MutableList<KClass<Any>> = mutableListOf()
    val file = File(path)?.walk()
    var classList : List<String> =  file
        .filter { if (fileFilter == null) true else fileFilter.invoke(it) }
        .filter { it.name.endsWith(".kt") or it.name.endsWith(".java") or it.name.endsWith(".class") }
        .map { if (fileMap == null) it else fileMap.invoke(it) }
        .map {
            println("====${it.name}")
            it.name }
        .toList()

    return result
}

