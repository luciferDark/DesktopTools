package com.ll.lib.utils

import java.io.File

class FileUtils {
    companion object {
        //获取当前程序运行的绝对路径
        val PROJECT_PATH: String = File("").canonicalPath

        val PROJECT_CONFIG_PATH = PROJECT_PATH + File.separator + "configs" + File.separator
    }
}