package com.vfs.recipenlpapp.views

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.TextClassificationClient
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.recipelist.RecipeAdapter

class MainActivity : AppCompatActivity(), RecipeClickListener {

    lateinit var model : TextClassificationClient
    lateinit var recipeAdapter : RecipeAdapter
    lateinit var recipeRV : RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Data.initiateData(application)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        model = TextClassificationClient(context)
        model.load("FoodTimeModel.tflite")

        return super.onCreateView(name, context, attrs)
    }

    override fun onStart() {
        super.onStart()

        recipeAdapter = RecipeAdapter(this)
        recipeRV = findViewById(R.id.rv_recipe_list)
        recipeRV.adapter = recipeAdapter
    }

    override fun OnGotoLinkClicked(recipeIndex : Int) {
        val intent = Intent(applicationContext, StoredRecipeDetails::class.java)
        intent.putExtra("recipeIndex", recipeIndex)
        startActivity(intent)
    }
}