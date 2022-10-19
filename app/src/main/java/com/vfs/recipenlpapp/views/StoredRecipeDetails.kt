package com.vfs.recipenlpapp.views

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.vfs.recipenlpapp.R


class StoredRecipeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stored_recipe_details)

        val bundle = bundleOf("recipeIndexParam" to 0)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<StoredRecipeWebViewFragment>(R.id.StoredRecipesFragment, args = bundle)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}