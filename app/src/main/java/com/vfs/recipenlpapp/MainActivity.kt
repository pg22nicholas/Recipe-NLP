package com.vfs.recipenlpapp

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.vfs.recipenlpapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var model : TextClassificationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
}