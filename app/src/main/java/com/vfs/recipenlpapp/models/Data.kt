package com.vfs.recipenlpapp.models

import android.app.Application

class Data {

    companion object {

        var recipeList : MutableList<Recipe> = mutableListOf()

        lateinit var application : Application

        fun initiateData(application: Application) {
            this.application = application

            recipeList.add(Recipe("test1", "www.google.com"))
            recipeList.add(Recipe("test2", "www.google.com"))
        }
    }
}