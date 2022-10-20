package com.vfs.recipenlpapp.views

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.TextClassificationClient
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.models.Ingredient
import com.vfs.recipenlpapp.models.Recipe
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
            addRecipeDialog()
        }
    }

    override fun OnGotoLinkClicked(recipeIndex : Int) {
        val intent = Intent(applicationContext, StoredRecipeDetails::class.java)
        intent.putExtra("recipeIndex", recipeIndex)
        startActivity(intent)
    }

    override fun OnLongPressed(recipeIndex: Int) {
        deleteRecipeDialog(recipeIndex)
    }

    private fun deleteRecipeDialog(recipeIndex: Int) {
        val recipe = Data.recipeList[recipeIndex]
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


    fun addRecipeDialog() {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Title")

        val addRecipeCustomView = layoutInflater.inflate(R.layout.add_recipe_dialog,null)
        builder.setView(addRecipeCustomView)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            val recipeName = addRecipeCustomView.findViewById<EditText>(R.id.addRecipeNameDialogEditText).text.toString()
            val ingredients = addRecipeCustomView.findViewById<EditText>(R.id.addRecipeIngredientsDialogEditText).text.toString()
            CreateRecipeFromText(recipeName, ingredients);
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    private fun CreateRecipeFromText(recipeName : String, ingredients : String) {
        val ingredientStringList = ingredients.split(" ")
        val ingredientList = mutableListOf<Ingredient>()
        for (ingredient in ingredientStringList) {
            ingredientList.add(Ingredient(ingredient))
        }
        Data.AddRecipe(Recipe(recipeName, "",ingredientList))
        recipeAdapter.notifyDataSetChanged()
    }
}