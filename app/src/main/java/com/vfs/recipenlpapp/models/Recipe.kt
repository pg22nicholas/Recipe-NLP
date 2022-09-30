package com.vfs.recipenlpapp.models

import android.content.Context
import com.vfs.recipenlpapp.Result
import com.vfs.recipenlpapp.TextClassificationClient

class Recipe(var link : String, var ingredientList : MutableList<Ingredient> = mutableListOf()) {

    lateinit var resultList : List<Result>

    fun AddIngredient(IngredientToAdd : Ingredient) {
        ingredientList.add(IngredientToAdd)
    }

    fun RunTextClassification(context : Context) {
        val model : TextClassificationClient
        model = TextClassificationClient(context)
        model.load()
        val checkString = ConvertIngredientsToTextClassificationInput()
        resultList = model.classify(checkString)
    }

    fun ConvertIngredientsToTextClassificationInput() : String {
        var result = ""
        for (ingredient in ingredientList) {
            result += ingredient.Title + " "
        }
        return result
    }
}