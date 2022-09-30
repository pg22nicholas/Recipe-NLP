package com.vfs.recipenlpapp.recipelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.models.Recipe


class RecipeCategoryViewHolder(rootLayout : LinearLayout) : RecyclerView.ViewHolder(rootLayout) {

    var recipeCategoryNameTextView : TextView? = null


    init {
        recipeCategoryNameTextView = itemView.findViewById(R.id.text_recipe_category)
    }

    fun bindRecipeCategory(recipeCategory: String?) {

        recipeCategoryNameTextView?.let {
            it.text = recipeCategory
        }
    }
}

class RecipeCategoryAdapter(var recipe : Recipe) : RecyclerView.Adapter<RecipeCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoryViewHolder {
        val categoryRoot = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recipe_category_item, parent, false)
        return RecipeCategoryViewHolder(categoryRoot as LinearLayout)
    }

    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {

        holder.bindRecipeCategory(recipe.resultList[position].title)
    }

    override fun getItemCount(): Int {
        return recipe.resultList.count()
    }
}