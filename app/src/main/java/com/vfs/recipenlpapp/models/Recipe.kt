package com.vfs.recipenlpapp.models

import android.content.Context
import com.vfs.recipenlpapp.Result
import com.vfs.recipenlpapp.TextClassificationClient

class Recipe(var title : String, var link : String, var ingredientList : MutableList<Ingredient> = mutableListOf()) {

    val ModelsArray : List<String> = mutableListOf("FoodTimeModel", "CuisineModel");
    var categoryList : MutableList<Result> = mutableListOf()

    fun AddIngredient(IngredientToAdd : Ingredient) {
        ingredientList.add(IngredientToAdd)
    }

    // run text classification on all models
    fun RunTextClassification(context : Context) {
        // Loops through all models and calculate the categories associated with it
        for (modelName in ModelsArray) {
            val model : TextClassificationClient
            model = TextClassificationClient(context)
            model.load(modelName + ".tflite")
            val checkString = ConvertIngredientsToTextClassificationInput()

            // get the results and store the best fitting result into the category list
            val resultList = model.classify(checkString) as MutableList<Result>
            if (resultList.size > 0) {
                categoryList.add(resultList[0])
            }
        }
//        categoryList.add(Result("0", "Lunch", 90F))
//        categoryList.add(Result("1", "Mexican", 85F))
    }

    // Convert the list of ingredients to a single string for NLP processing
    fun ConvertIngredientsToTextClassificationInput() : String {
        var result = ""
        for (ingredient in ingredientList) {
            result += ingredient.Title + " "
        }
        return result
    }
}