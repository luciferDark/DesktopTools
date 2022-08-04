package reflects

import models.TabModels
import org.reflections.Reflections
import java.io.File
import java.lang.reflect.Method
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.functions

class ApplicationContext {
    companion object {
        private var instance: ApplicationContext? = null

        @Synchronized
        fun instance(): ApplicationContext {
            if (null == instance) {
                instance = ApplicationContext()
            }
            return instance!!
        }
    }

    //存放所有添加注解规则的类的实例bean
    private var projectBeansClassDefine = mutableMapOf<String, ClassBeanDefine>()

    private var projectBeans = mutableMapOf<String, Any>()
    //存放所有添加注解规则的类的实例的单例bean
    private var projectSingleBeans = mutableMapOf<String, Any>()


    private val SCAN_URL = "com.ll.project"
    private var scanFinished = false

    fun scan(force: Boolean = false) {
        if (scanFinished && !force) return
        println("start to scan")
        var classLoaderMain = Thread.currentThread().contextClassLoader

        //拿取class字节码路径地址
        var classPaths = classLoaderMain.getResources(".").toList()
            .filter { it.path.contains("/build/classes") }.map {
                if (it.path.startsWith("/")) {
                    it.path.substring(1, it.path.length)
                } else {
                    it.path
                }
            }

        classPaths.forEach {
            println("classLoaderMain path：$it")
            //扫描指定目录下的文件，获得类全名 SCAN_URL 可以动态获取
            var scanResult = findAllClassesInDir(it + SCAN_URL.replace(".", "/"))

            scanResult
                ?.map {
                    val clazz = classLoaderMain.loadClass(it)
                    clazz
                }
                ?.filter {
                    println("filter Class<Any> : ${it.name}")
                    it.isAnnotationPresent(ProjectAnnotation::class.java)
                }
                ?.forEach {
                    postHandleClass(it)
                }
        }
    }

    /**
     * 扫描 /build/classes 指定目录下的文件，获得类全名  class 文件类型
     */
    fun findAllClassesInDir(
        path: String, fileMap: ((File) -> File)? = null, fileFilter: ((File) -> Boolean)? = null
    ): List<String>? {
        val file = File(path)?.walk()
        val postFix = "main\\"
        var classList: List<String> = file.filter { if (fileFilter == null) true else fileFilter.invoke(it) }
            .filter { it.name.endsWith(".kt") or it.name.endsWith(".java") or it.name.endsWith(".class") }
            .map { if (fileMap == null) it else fileMap.invoke(it) }.map {
                val path = it.absolutePath
                path.substring(path.indexOf(postFix) + postFix.length, it.absolutePath.lastIndexOf("."))
                    .replace("\\", ".")
            }.toList()

        return classList
    }

    /**
     * 对扫描结果的类做进一步处理
     */
    fun postHandleClass(clazz : Class<*>){
        var annotation = clazz.getAnnotation(ProjectAnnotation::class.java)
        val annotationValue = annotation.value

        if(!projectBeansClassDefine.containsKey(clazz.name)){
            projectBeansClassDefine.put(clazz.name, ClassBeanDefine(clazz.name, clazz))
        }

        val any = clazz.kotlin.createInstance()
        var isProjectInterface = false
        clazz.interfaces.map{
            if(it.name.equals(ProjectInterface::class.java)) it
        }.forEach{
            onPostInitFinish(clazz, any)
        }
        when(annotationValue) {
            ProjectAnnotationType.NORMAL -> {
                println("${clazz.name} is NORMAL type")
                if(!projectBeans.containsKey(clazz.name)){
                    projectBeans.put(clazz.name, any)
                }
            }
            ProjectAnnotationType.INSTANCE -> {
                println("${clazz.name} is INSTANCE type")
                if(!projectSingleBeans.containsKey(clazz.name)){
                    projectSingleBeans.put(clazz.name, any)
                }
            }
        }
    }

    private fun onPostInitFinish(clazz: Class<*>, any: Any?) {
        clazz.declaredMethods.filter {
            it.name.equals("onPostInit")
        }.forEach{
            it.invoke(any)
        }
    }

}