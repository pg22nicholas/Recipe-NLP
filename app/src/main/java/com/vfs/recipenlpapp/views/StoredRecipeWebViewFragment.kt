package com.vfs.recipenlpapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.vfs.recipenlpapp.R
import com.vfs.recipenlpapp.models.Data
import com.vfs.recipenlpapp.models.Recipe

private const val ARG_RECIPE_INDEX = "recipeIndexParam"

class StoredRecipeWebViewFragment : Fragment() {

    private var recipeIndex: Int? = null
    private lateinit var recipe : Recipe;

    private lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeIndex = it.getInt(ARG_RECIPE_INDEX)
            recipe = Data.recipeList[recipeIndex!!]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stored_recipe_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.storedRecipeWebView)!!
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(recipe.link)
    }

    companion object {
        @JvmStatic
        fun newInstance(recipeIndex: Int) =
            StoredRecipeWebViewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_RECIPE_INDEX, recipeIndex)
                }
            }
    }
}