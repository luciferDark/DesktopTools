package models

import interfaces.MainUIPages
import reflects.ProjectAnnotation

@ProjectAnnotation
class TabModels(tabs: MutableMap<String, MainUIPages> = mutableMapOf()) {
    companion object {
        private var instance: TabModels? = null

        @Synchronized
        fun instance(): TabModels {
            if (null == instance) {
                instance = TabModels()
            }
            return instance!!
        }
    }

    private var tabs: MutableMap<String, MainUIPages> = mutableMapOf()
    var indexKey: String? = null

    fun addTab(key: String, page: MainUIPages) {
        tabs.apply {
            if (!contains(key)) {
                put(key, page)
            }
        }
    }

    fun removeTab(key: String, page: MainUIPages) {
        tabs.apply {
            if (!contains(key)) {
                remove(key)
            }
        }
    }

    fun tabList() : List<MainUIPages> = tabs.map { entry ->
        entry.value
    }

    fun getTab(key: String) = tabs[key]

    fun printStr(msg : String) = println("call ${this::class.simpleName} method printStr, arg = $msg")

}