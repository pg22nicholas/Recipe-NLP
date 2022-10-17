package com.vfs.recipenlpapp.recipelist

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.models.Recipe

class RecipeViewHolder(rootLayout : LinearLayout) : RecyclerView.ViewHolder(rootLayout) {

    var recipeNameTextView : TextView? = null
    var recipeContainer : LinearLayout? = null
    var recipeLinkAccessor : FrameLayout? = null;
    var recipeCategoryList : RecyclerView? = null;


    init {
        recipeNameTextView = itemView.findViewById(R.id.text_recipe_name)
        recipeContainer = itemView.findViewById(R.id.recipe_row_container)
        recipeLinkAccessor = itemView.findViewById(R.id.recipe_link_accessor)
        recipeCategoryList = itemView.findViewById(R.id.rv_recipe_categories)
    }

    fun bindRecipe(recipe: Recipe) {

        recipeNameTextView?.let {
            it.text = recipe.title
        }
    }
}

class RecipeAdapter(val listener : RecipeClickListener) : RecyclerView.Adapter<RecipeViewHolder>() {

    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val categoryRoot = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recipe_row, parent, false)
        context = parent.context
        return RecipeViewHolder(categoryRoot as LinearLayout)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val recipe = Data.recipeList[position]

        val flexboxLayoutManager = FlexboxLayoutManager(context)
        holder.recipeCategoryList?.layoutManager = flexboxLayoutManager
        holder.recipeCategoryList?.adapter = RecipeCategoryAdapter(recipe)

        holder.recipeLinkAccessor?.setOnClickListener {
            listener.OnGotoLinkClicked(Data.recipeList[holder.adapterPosition].link)
        }
        holder.bindRecipe(recipe)
    }

    override fun getItemCount(): Int {
        return Data.recipeList.count()
    }
}