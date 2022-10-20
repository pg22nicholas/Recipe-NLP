package com.vfs.recipenlpapp.views

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.TextClassificationClient
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.recipelist.RecipeAdapter

class MainActivity : AppCompatActivity(), RecipeClickListener {

    lateinit var model : TextClassificationClient
    lateinit var recipeAdapter : RecipeAdapter
    lateinit var recipeRV : RecyclerView;
    lateinit var AddRecipeFab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Data.initiateData(application)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "NLP Recipe App"
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

        findViewById<FloatingActionButton>(R.id.add_recipe_fab).setOnClickListener {
            Snackbar.make(findViewById<View>(android.R.id.content).getRootView(), "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

        }
    }

    override fun OnGotoLinkClicked(recipeIndex : Int) {
        val intent = Intent(applicationContext, StoredRecipeDetails::class.java)
        intent.putExtra("recipeIndex", recipeIndex)
        startActivity(intent)
    }

    override fun OnLongPressed(recipeIndex: Int) {
        var recipe = Data.recipeList[recipeIndex]
        AlertDialog.Builder(this)
            .setMessage("Do you want to delete " + recipe.title + "?")
            .setCancelable(true)
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
                Data.removeRecipe(recipeIndex)
                recipeAdapter.notifyDataSetChanged()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            .create()
            .show()
    }
}