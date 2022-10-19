package com.vfs.recipenlpapp.models

import android.app.Application
import android.content.Context
import com.vfs.recipenlpapp.Result

class Data {

    companion object {

        var recipeList : MutableList<Recipe> = mutableListOf()

        lateinit var application : Application

        fun initiateData(application: Application) {
            this.application = application

            val ingredients1 : MutableList<Ingredient> = mutableListOf(Ingredient("Sugar"), Ingredient("flour"), Ingredient("Baking Soda"))
            AddRecipe(Recipe("test1", "https://www.allrecipes.com/recipe/8459990/rutabaga-beef-stew/", ingredients1))
            val ingredients2 : MutableList<Ingredient> = mutableListOf(Ingredient("eggs"), Ingredient("bread"), Ingredient("avacado"))
            AddRecipe(Recipe("test2", "https://www.allrecipes.com/recipe/8459990/rutabaga-beef-stew/", ingredients2))
        }

        fun AddRecipe(recipe: Recipe) {
            recipe.RunTextClassification(application)
            recipeList.add(recipe);
        }
    }
}