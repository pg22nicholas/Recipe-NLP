package com.vfs.recipenlpapp

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vfs.recipenlpapp.Listeners.RecipeClickListener
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

        findViewById<Button>(R.id.Check_Button).setOnClickListener {
            val checkString = findViewById<EditText>(R.id.SentenceCheck_EditText)
            val result: List<Result> = model.classify(checkString.text.toString())
            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        model = TextClassificationClient(context)
        model.load()

        return super.onCreateView(name, context, attrs)
    }

    override fun onStart() {
        super.onStart()

        recipeAdapter = RecipeAdapter(this)
        recipeRV = findViewById(R.id.rv_recipe_list)
        recipeRV.adapter = recipeAdapter
    }

    override fun OnGotoLinkClicked(link: String) {
        // TODO
        Toast.makeText(applicationContext, "goto link: " + link, Toast.LENGTH_SHORT).show()
    }
}