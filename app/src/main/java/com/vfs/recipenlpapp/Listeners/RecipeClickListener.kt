package com.vfs.recipenlpapp.Listeners

interface RecipeClickListener {

    fun OnGotoLinkClicked(recipeIndex : Int)
    fun OnLongPressed(recipeIndex : Int)
}