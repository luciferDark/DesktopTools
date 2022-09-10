package reflects

/**
 * 工程模块的注解
 * 必须继承@ProjectInterface 才能起作用
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProjectAnnotation(val value : ProjectAnnotationType = ProjectAnnotationType.NORMAL)

public enum class ProjectAnnotationType {
    INSTANCE, //单例模式
    NORMAL,   //默认实例类
}