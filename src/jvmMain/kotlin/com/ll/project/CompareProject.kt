package com.ll.project

import reflects.ProjectAnnotation
import reflects.ProjectAnnotationType
import reflects.ProjectInterface

@ProjectAnnotation(ProjectAnnotationType.INSTANCE)
class CompareProject : ProjectInterface {
    override fun onPostInit() {
        println("CompareProject onPostInit")
    }
}