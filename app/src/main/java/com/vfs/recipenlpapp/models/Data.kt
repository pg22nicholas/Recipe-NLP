package com.vfs.recipenlpapp.models

import android.app.Application
import android.content.Context
import com.vfs.recipenlpapp.Result

class Data {

    companion object {

        var recipeIDTracker = 0;

        // For holding all recipes stored by user
        var recipeList : MutableList<Recipe> = mutableListOf()
        // For holding all recipes being displayed, separated for sorting and filtering purposes
        var recipeDisplayList : MutableList<Recipe> = mutableListOf()

        lateinit var application : Application

        fun initiateData(application: Application) {
            this.application = application

            val ingredients1 : MutableList<Ingredient> = mutableListOf(Ingredient("Sugar"), Ingredient("flour"), Ingredient("Baking Soda"))
            AddRecipe(Recipe("test1", "https://www.allrecipes.com/recipe/8459990/rutabaga-beef-stew/", ingredients1))
            val ingredients2 : MutableList<Ingredient> = mutableListOf(Ingredient("eggs"), Ingredient("bread"), Ingredient("avacado"))
            AddRecipe(Recipe("test2", "https://www.allrecipes.com/recipe/8459990/rutabaga-beef-stew/", ingredients2))

            recipeDisplayList = ArrayList(recipeList)
        }

        fun AddRecipe(recipe: Recipe) {
            recipe.RunTextClassification(application)
            recipeList.add(recipe);
            recipeDisplayList.add(recipe)
        }

        fun removeRecipe(index : Int) {
            val recipeIDToDelete = recipeDisplayList[index].ID
            recipeDisplayList.removeIf { it.ID == recipeIDToDelete }
            recipeList.removeIf { it.ID == recipeIDToDelete }
        }

        fun resetRecipeDisplayList() {
            recipeDisplayList = ArrayList(recipeList)
        }

        fun getNewRecipeID() : Int {
            return recipeIDTracker++
        }
    }
}