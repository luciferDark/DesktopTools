package reflects

/**
 * 工程模块的注解
 * 必须继承@ProjectInterface 才能起作用
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ProjectAnnotation
