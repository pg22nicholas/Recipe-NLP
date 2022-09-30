package com.vfs.recipenlpapp.models

import android.app.Application
import com.vfs.recipenlpapp.Result

class Data {

    companion object {

        var recipeList : MutableList<Recipe> = mutableListOf()

        lateinit var application : Application

        fun initiateData(application: Application) {
            this.application = application

            recipeList.add(Recipe("test1", "www.google.com"))
            recipeList[0].resultList.add(Result("0", "TestResult", 60F))
            recipeList[0].resultList.add(Result("0", "Tes1", 60F))
            recipeList[0].resultList.add(Result("0", "TestResult2", 60F))
            recipeList[0].resultList.add(Result("0", "TestRe", 60F))
            recipeList[0].resultList.add(Result("0", "TestResult3", 60F))
            recipeList[0].resultList.add(Result("0", "TestResult3", 60F))
            recipeList[0].resultList.add(Result("0", "TestRe", 60F))
            recipeList.add(Recipe("test2", "www.google.com"))
            recipeList[1].resultList.add(Result("0", "TestResult2", 20F))
        }
    }
}